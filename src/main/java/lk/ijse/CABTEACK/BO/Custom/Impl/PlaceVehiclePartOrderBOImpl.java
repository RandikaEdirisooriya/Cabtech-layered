package lk.ijse.CABTEACK.BO.Custom.Impl;

import javafx.scene.control.Alert;
import lk.ijse.CABTEACK.BO.Custom.PlaceVehiclePartOrderBO;
import lk.ijse.CABTEACK.DB.DatabaseConnection;
import lk.ijse.CABTEACK.DTO.vehiclePartOrderDetail;
import lk.ijse.CABTEACK.DTO.vehiclePartsOrder;
import lk.ijse.CABTEACK.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceVehiclePartOrderBOImpl implements PlaceVehiclePartOrderBO {
    public boolean placeOrder(vehiclePartsOrder order) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        boolean success = false;

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderInserted = CrudUtil.execute("INSERT INTO vehiclePartsOrder (partOrderId,orderDate,orderTime,cost,customerId) " +
                    "VALUES (?,?,?,?,?)",order.getPartOrderId(),order.getOrderDate(),order.getOrderTime(),order.getCost(),order.getCustomerId());



            if (isOrderInserted) {
                if (saveOrderDetail(order.getPartOrderId(), order.getDetails(), connection)) {
                    success = true;
                }
            }

            if (success) {
                connection.commit();
                new Alert(Alert.AlertType.CONFIRMATION, "Done").show();
            } else {
                connection.rollback();
                new Alert(Alert.AlertType.WARNING, "Not Done").show();
            }
        } catch (SQLException e) {
            connection.rollback();
            throw e; // Re-throw the exception after rolling back the transaction
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);

            }
        }

        return success;
    }

    public boolean saveOrderDetail(String partOrderId, ArrayList<vehiclePartOrderDetail> items, Connection connection) {
        for (vehiclePartOrderDetail temp : items) {
            String detailSql = "INSERT INTO vehiclePartsOrderDetails (partOrderId,partsId, qtyForSale, Price) VALUES (?,?,?,?)";

            try (PreparedStatement detailStm = connection.prepareStatement(detailSql)) {
                detailStm.setObject(1, partOrderId);
                detailStm.setObject(2, temp.getPartId());
                detailStm.setObject(3, temp.getQtyForSale());
                detailStm.setObject(4, temp.getAmount());

                if (detailStm.executeUpdate() <= 0) {
                    return false;
                }

                if (!updateQty(temp.getPartId(), temp.getQtyForSale(), connection)) {
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }

    public boolean updateQty(String partId, int qtyForSale, Connection connection) {
        String updateSql = "UPDATE vehicleparts SET qty = qty - ? WHERE partsId = ?";

        try (PreparedStatement updateStm = connection.prepareStatement(updateSql)) {
            updateStm.setInt(1, qtyForSale);
            updateStm.setString(2, partId);


            int affectedRows = updateStm.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

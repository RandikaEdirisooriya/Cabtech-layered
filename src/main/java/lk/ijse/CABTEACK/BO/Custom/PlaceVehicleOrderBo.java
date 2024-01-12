package lk.ijse.CABTEACK.BO.Custom;

import lk.ijse.CABTEACK.BO.SuperBO;
import lk.ijse.CABTEACK.DTO.VehicleOrderDetails;
import lk.ijse.CABTEACK.DTO.vehicleOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceVehicleOrderBo extends SuperBO {
    boolean placeOrder(vehicleOrder order) throws SQLException, ClassNotFoundException;
    boolean saveOrderDetail(String partOrderId, ArrayList<VehicleOrderDetails> items, Connection connection);
    boolean updateQty(String partId, int qtyForSale, Connection connection);
}

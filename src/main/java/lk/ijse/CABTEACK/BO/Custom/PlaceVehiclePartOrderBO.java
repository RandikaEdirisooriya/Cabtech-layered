package lk.ijse.CABTEACK.BO.Custom;

import lk.ijse.CABTEACK.BO.SuperBO;
import lk.ijse.CABTEACK.DTO.vehiclePartOrderDetail;
import lk.ijse.CABTEACK.DTO.vehiclePartsOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceVehiclePartOrderBO extends SuperBO {
    boolean placeOrder(vehiclePartsOrder order) throws SQLException, ClassNotFoundException;
    boolean saveOrderDetail(String partOrderId, ArrayList<vehiclePartOrderDetail> items, Connection connection);
    boolean updateQty(String partId, int qtyForSale, Connection connection);
}

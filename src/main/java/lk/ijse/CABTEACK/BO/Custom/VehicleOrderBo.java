package lk.ijse.CABTEACK.BO.Custom;

import lk.ijse.CABTEACK.BO.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface VehicleOrderBo extends SuperBO {
    List<String> getVehicleOrderAllIds() throws SQLException, ClassNotFoundException;
}

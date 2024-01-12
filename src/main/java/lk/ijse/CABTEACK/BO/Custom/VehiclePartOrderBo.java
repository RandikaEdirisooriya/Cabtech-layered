package lk.ijse.CABTEACK.BO.Custom;

import lk.ijse.CABTEACK.BO.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface VehiclePartOrderBo extends SuperBO {
    List<String> getVehiclePartOrderAllIds() throws SQLException, ClassNotFoundException;
}

package lk.ijse.CABTEACK.BO.Custom;

import lk.ijse.CABTEACK.BO.SuperBO;
import lk.ijse.CABTEACK.DTO.VehicleParts;
import lk.ijse.CABTEACK.DTO.vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehiclepartBo extends SuperBO {
    ArrayList<VehicleParts> getAllVehiclePart() throws SQLException, ClassNotFoundException;
    List<String> getVehicleIdsIds() throws SQLException, ClassNotFoundException;
    boolean AddVehicleParts(VehicleParts c) throws SQLException, ClassNotFoundException;
}

package lk.ijse.CABTEACK.BO.Custom;

import lk.ijse.CABTEACK.BO.SuperBO;
import lk.ijse.CABTEACK.DTO.vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CarsBo extends SuperBO {
    ArrayList<vehicle> getAllVehicle() throws SQLException, ClassNotFoundException;
     boolean AddVehicle(vehicle v1) throws SQLException, ClassNotFoundException;
    List<String> getVehicleIds() throws SQLException, ClassNotFoundException;
    List<String> getVehicleAllIds() throws SQLException, ClassNotFoundException;
    public boolean updateVehicle(vehicle v1) throws SQLException, ClassNotFoundException;
}

package lk.ijse.CABTEACK.Controller.FuntionalController;

import lk.ijse.CABTEACK.DAO.SqlUtil;

import lk.ijse.CABTEACK.DTO.VehicleParts;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiclePartsController {

    public int getAllItem() throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SqlUtil.execute("SELECT COUNT(partsId) as partsId FROM vehicleParts");
        int partId=0;
        while (resultSet.next()){
            partId=resultSet.getInt("partsId");
        }
        return partId;
    }
}

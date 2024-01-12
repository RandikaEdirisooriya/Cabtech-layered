package lk.ijse.CABTEACK.DAO.Custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.CABTEACK.DAO.Custom.VehiclePartsDao;
import lk.ijse.CABTEACK.DAO.SqlUtil;
import lk.ijse.CABTEACK.ENTITY.VehiclePartsEntity;
import lk.ijse.CABTEACK.TM.VehiclePartsTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiclePartDAOImpl implements VehiclePartsDao {

    @Override
    public ArrayList<VehiclePartsEntity> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<VehiclePartsEntity> part = new ArrayList<>();
        ResultSet res = SqlUtil.execute("SELECT * FROM vehicleParts");

        while (res.next()) {
            VehiclePartsEntity entity=new VehiclePartsEntity(
                    res.getString(1),
                    res.getInt(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6),
                    res.getDouble(7)

            );
            part.add(entity);
        }
        return part;
    }

    @Override
    public boolean save(VehiclePartsEntity c) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute(
                "INSERT INTO vehicleparts (partsId, qty, code, colour, description, nameOfVehiclePart, Price) VALUES (?, ?, ?, ?, ?, ?, ?)",
                c.getPartsId(), String.valueOf(c.getQty()), c.getCode(), c.getColour(), c.getDescription(), c.getNameOfVehiclePart(), String.valueOf(c.getPrice())
        );
    }

    @Override
    public boolean update(VehiclePartsEntity dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public VehiclePartsEntity search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet res= SqlUtil.execute("SELECT * FROM vehicleparts");
        List<String> ids=new ArrayList<>();
        while (res.next()){
            ids.add(
                    res.getString(1)
            );

        }
        return  ids;
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}

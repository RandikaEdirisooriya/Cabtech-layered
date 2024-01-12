package lk.ijse.CABTEACK.DAO.Custom.Impl;

import lk.ijse.CABTEACK.DAO.Custom.VehiclePartOrderDao;
import lk.ijse.CABTEACK.DAO.SqlUtil;
import lk.ijse.CABTEACK.ENTITY.VehiclePartsEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiclePartOrderDAOImpl implements VehiclePartOrderDao {
    @Override
    public ArrayList<VehiclePartsEntity> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(VehiclePartsEntity dto) throws SQLException, ClassNotFoundException {
        return false;
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
        ResultSet res= SqlUtil.execute("SELECT * FROM vehiclePartsOrder");
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

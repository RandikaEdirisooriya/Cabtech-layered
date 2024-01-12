package lk.ijse.CABTEACK.DAO.Custom.Impl;

import lk.ijse.CABTEACK.DAO.CrudDAO;
import lk.ijse.CABTEACK.DAO.Custom.BikeDao;
import lk.ijse.CABTEACK.DAO.SqlUtil;
import lk.ijse.CABTEACK.ENTITY.VehicleEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BikeDAOImpl implements BikeDao {
    @Override
    public ArrayList<VehicleEntity> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<VehicleEntity> bike = new ArrayList<>();

        ResultSet res =SqlUtil.execute("SELECT * FROM vehicle WHERE vehicleType = 'Bike'"); {


            while (res.next()) {

                VehicleEntity entity=new VehicleEntity(
                        res.getString(1), // Set ID
                        res.getString(3), // Set type
                        res.getString(4), // Set name
                        res.getString(5),// Set colour
                        res.getDouble(6),// Set description
                        res.getInt(2)


                );
                bike.add(entity);
            }
            return bike;
        }
    }

    @Override
    public boolean save(VehicleEntity v1) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute(
                "INSERT INTO vehicle (vehicleId, qty, vehicleType, nameOfVehicle, colour, price) VALUES (?, ?, ?, ?, ?, ?)",
                v1.getVehicleId(), v1.getQty(), v1.getVehicleType(), v1.getNameOfVehicle(), v1.getColour(), v1.getPrice()
        );
    }

    @Override
    public boolean update(VehicleEntity v1) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute(
                "UPDATE vehicle SET qty=?,vehicleType=?,nameOfVehicle=?,colour=? ,price=? WHERE vehicleId=?",
                v1.getQty(),  v1.getVehicleType(), v1.getNameOfVehicle(), v1.getColour(), v1.getPrice(),v1.getVehicleId()
        );    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public VehicleEntity search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet res=SqlUtil.execute("SELECT * FROM vehicle WHERE vehicleType = 'Bike'");
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
        ResultSet res=SqlUtil.execute("SELECT * FROM vehicle");
        List<String> ids=new ArrayList<>();
        while (res.next()){
            ids.add(
                    res.getString(1)
            );

        }
        return  ids;

    }
}

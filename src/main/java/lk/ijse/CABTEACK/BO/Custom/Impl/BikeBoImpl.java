package lk.ijse.CABTEACK.BO.Custom.Impl;

import lk.ijse.CABTEACK.BO.Custom.BikeBo;
import lk.ijse.CABTEACK.DAO.Custom.BikeDao;
import lk.ijse.CABTEACK.DAO.Custom.CarsDao;
import lk.ijse.CABTEACK.DAO.DAOFactory;
import lk.ijse.CABTEACK.DTO.vehicle;
import lk.ijse.CABTEACK.ENTITY.VehicleEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BikeBoImpl implements BikeBo {
    BikeDao bikeDao =
            (BikeDao) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.BIKE);
    @Override
    public ArrayList<vehicle> getAllVehicle() throws SQLException, ClassNotFoundException {
        ArrayList<VehicleEntity> bike = bikeDao.getAll();
        ArrayList<vehicle> vehicleDtos = new ArrayList<>();

        for (VehicleEntity vehicleEntity : bike) {
            vehicle vehicleDto = new vehicle(
                    vehicleEntity.getVehicleId(),
                    vehicleEntity.getVehicleType(),
                    vehicleEntity.getNameOfVehicle(),
                    vehicleEntity.getColour(),
                    vehicleEntity.getPrice(),
                    vehicleEntity.getQty()
            );
            vehicleDtos.add(vehicleDto);
        }

        return vehicleDtos;
    }

    @Override
    public boolean AddVehicle(vehicle v1) throws SQLException, ClassNotFoundException {
        return bikeDao.save(new VehicleEntity(   v1.getVehicleId(), v1.getVehicleType(), v1.getNameOfVehicle(), v1.getColour(), v1.getPrice(), v1.getQty()));
    }

    @Override
    public List<String> getVehicleIds() throws SQLException, ClassNotFoundException {
        return bikeDao.getIds();
    }

    @Override
    public List<String> getVehicleAllIds() throws SQLException, ClassNotFoundException {
        return bikeDao.getAllIds();
    }

    @Override
    public boolean updateVehicle(vehicle v1) throws SQLException, ClassNotFoundException {
        return bikeDao.update(new VehicleEntity(   v1.getVehicleId(), v1.getVehicleType(), v1.getNameOfVehicle(), v1.getColour(), v1.getPrice(), v1.getQty()));
    }
}

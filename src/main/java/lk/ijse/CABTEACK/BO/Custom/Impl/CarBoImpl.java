package lk.ijse.CABTEACK.BO.Custom.Impl;

import lk.ijse.CABTEACK.BO.Custom.CarsBo;
import lk.ijse.CABTEACK.DAO.Custom.CarsDao;
import lk.ijse.CABTEACK.DAO.DAOFactory;
import lk.ijse.CABTEACK.DTO.vehicle;
import lk.ijse.CABTEACK.ENTITY.VehicleEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarBoImpl implements CarsBo {
    CarsDao carsDao =
            (CarsDao) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.CAR);
    @Override
    public ArrayList<vehicle> getAllVehicle() throws SQLException, ClassNotFoundException {
        ArrayList<VehicleEntity> cars = carsDao.getAll();
        ArrayList<vehicle> vehicleDtos = new ArrayList<>();

        for (VehicleEntity vehicleEntity : cars) {
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
/*
  return customerDAO.save(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
  String vehicleId, String vehicleType, String nameOfVehicle, String colour, double price, int qty
*/
    @Override
    public boolean AddVehicle(vehicle v1) throws SQLException, ClassNotFoundException {
  return carsDao.save(new VehicleEntity(   v1.getVehicleId(), v1.getVehicleType(), v1.getNameOfVehicle(), v1.getColour(), v1.getPrice(), v1.getQty()));
    }

    @Override
    public List<String> getVehicleIds() throws SQLException, ClassNotFoundException {
      return carsDao.getIds();
    }

    @Override
    public List<String> getVehicleAllIds() throws SQLException, ClassNotFoundException {
        return carsDao.getAllIds();
    }

    @Override
    public boolean updateVehicle(vehicle v1) throws SQLException, ClassNotFoundException {
        return carsDao.update(new VehicleEntity(   v1.getVehicleId(), v1.getVehicleType(), v1.getNameOfVehicle(), v1.getColour(), v1.getPrice(), v1.getQty()));


    }

}
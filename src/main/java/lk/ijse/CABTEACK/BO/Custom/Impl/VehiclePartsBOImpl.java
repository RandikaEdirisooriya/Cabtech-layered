package lk.ijse.CABTEACK.BO.Custom.Impl;

import lk.ijse.CABTEACK.BO.Custom.VehiclepartBo;
import lk.ijse.CABTEACK.DAO.Custom.CarsDao;
import lk.ijse.CABTEACK.DAO.Custom.VehiclePartsDao;
import lk.ijse.CABTEACK.DAO.DAOFactory;
import lk.ijse.CABTEACK.DTO.VehicleParts;
import lk.ijse.CABTEACK.DTO.vehicle;
import lk.ijse.CABTEACK.ENTITY.VehicleEntity;
import lk.ijse.CABTEACK.ENTITY.VehiclePartsEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiclePartsBOImpl implements VehiclepartBo {
    VehiclePartsDao vehiclePartsDao =
            (VehiclePartsDao) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.VEHICLEPARTS);
    @Override
    public ArrayList<VehicleParts> getAllVehiclePart() throws SQLException, ClassNotFoundException {
        ArrayList<VehiclePartsEntity> part = vehiclePartsDao.getAll();
        ArrayList<VehicleParts> partDtos = new ArrayList<>();


        for (VehiclePartsEntity vehiclePartsEntity : part) {
            VehicleParts parDto = new VehicleParts(
                    vehiclePartsEntity.getPartsId(),
                    vehiclePartsEntity.getQty(),
                    vehiclePartsEntity.getCode(),
                    vehiclePartsEntity.getColour(),
                    vehiclePartsEntity.getDescription(),
                    vehiclePartsEntity.getNameOfVehiclePart(),
                    vehiclePartsEntity.getPrice()
            );
            partDtos.add(parDto);
        }

        return partDtos;
    }

    @Override
    public List<String> getVehicleIdsIds() throws SQLException, ClassNotFoundException {
        return vehiclePartsDao.getIds();
    }

    @Override
    public boolean AddVehicleParts(VehicleParts vehiclePartsEntity) throws SQLException, ClassNotFoundException {
        return vehiclePartsDao.save(new VehiclePartsEntity(   vehiclePartsEntity.getPartsId(),
                vehiclePartsEntity.getQty(),
                vehiclePartsEntity.getCode(),
                vehiclePartsEntity.getColour(),
                vehiclePartsEntity.getDescription(),
                vehiclePartsEntity.getNameOfVehiclePart(),
                vehiclePartsEntity.getPrice()));

    }
}


package lk.ijse.CABTEACK.BO.Custom.Impl;

import lk.ijse.CABTEACK.BO.Custom.VehicleOrderBo;
import lk.ijse.CABTEACK.DAO.Custom.ThreeWeelDao;
import lk.ijse.CABTEACK.DAO.Custom.VehicleOrderDao;
import lk.ijse.CABTEACK.DAO.DAOFactory;

import java.sql.SQLException;
import java.util.List;

public class VehicleOrderBOImpl implements VehicleOrderBo {
    VehicleOrderDao vehicleOrderDao =
            (VehicleOrderDao) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.VEHICLEORDER);
    @Override
    public List<String> getVehicleOrderAllIds() throws SQLException, ClassNotFoundException {
        return vehicleOrderDao.getIds();
    }
}

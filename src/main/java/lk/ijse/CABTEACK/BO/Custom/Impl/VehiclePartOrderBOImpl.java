package lk.ijse.CABTEACK.BO.Custom.Impl;

import lk.ijse.CABTEACK.BO.Custom.VehiclePartOrderBo;
import lk.ijse.CABTEACK.DAO.Custom.VehicleOrderDao;
import lk.ijse.CABTEACK.DAO.DAOFactory;

import java.sql.SQLException;
import java.util.List;

public class VehiclePartOrderBOImpl implements VehiclePartOrderBo {
    VehiclePartOrderBo vehiclePartOrderBo =
            (VehiclePartOrderBo) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.VEHICLEORDER);
    @Override
    public List<String> getVehiclePartOrderAllIds() throws SQLException, ClassNotFoundException {
        return vehiclePartOrderBo.getVehiclePartOrderAllIds();
    }
}

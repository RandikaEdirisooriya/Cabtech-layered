package lk.ijse.CABTEACK.BO.Custom.Impl;

import lk.ijse.CABTEACK.BO.Custom.ProfileBo;
import lk.ijse.CABTEACK.DAO.Custom.CustomerDao;
import lk.ijse.CABTEACK.DAO.Custom.ProfileDao;
import lk.ijse.CABTEACK.DAO.DAOFactory;
import lk.ijse.CABTEACK.DTO.admin;
import lk.ijse.CABTEACK.ENTITY.AdminEntity;
import lk.ijse.CABTEACK.ENTITY.CustomerEntity;

import java.sql.SQLException;
import java.util.List;

public class ProfileBOImpl implements ProfileBo {
    ProfileDao profileDao =
            (ProfileDao) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.PROFILE);
    @Override
    public boolean AddProfile(admin dto) throws SQLException, ClassNotFoundException {

        return profileDao.save(new AdminEntity(dto.getAdminId(),dto.getUsername(),dto.getPassword(),dto.getEmail()));

    }

    @Override
    public List<String> getUserIds() throws SQLException, ClassNotFoundException {
        return profileDao.getIds();
    }
}

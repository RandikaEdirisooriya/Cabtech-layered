package lk.ijse.CABTEACK.DAO.Custom.Impl;

import lk.ijse.CABTEACK.DAO.Custom.ProfileDao;
import lk.ijse.CABTEACK.DAO.SqlUtil;
import lk.ijse.CABTEACK.ENTITY.AdminEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAOImpl implements ProfileDao {
    @Override
    public ArrayList<AdminEntity> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(AdminEntity dto) throws SQLException, ClassNotFoundException {
      return   SqlUtil.execute("INSERT INTO admin VALUES (?,?,?,?)",dto.getAdminId(),dto.getUsername(),dto.getPassword(),dto.getEmail());
    }

    @Override
    public boolean update(AdminEntity dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public AdminEntity search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.execute("SELECT adminId FROM admin");
        List<String> ids = new ArrayList<>();
        while (resultSet.next()) {
            ids.add(resultSet.getString("adminId"));
        }
        return ids;
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}

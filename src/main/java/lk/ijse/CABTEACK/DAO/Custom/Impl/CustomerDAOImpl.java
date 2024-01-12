package lk.ijse.CABTEACK.DAO.Custom.Impl;

import lk.ijse.CABTEACK.DAO.Custom.CustomerDao;
import lk.ijse.CABTEACK.DAO.SqlUtil;
import lk.ijse.CABTEACK.ENTITY.CustomerEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDao {
    @Override
    public ArrayList<CustomerEntity> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(CustomerEntity c) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO customer (customerId, name, address, nic_number, contactNumber) VALUES (?, ?, ?, ?, ?)",
                c.getCustomerId(), c.getName(), c.getAddress(), c.getNic_number(), c.getContactNumber());
    }

    @Override
    public boolean update(CustomerEntity dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public CustomerEntity search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT customerId FROM customer");

        List<String> ids = new ArrayList<>();
        while (resultSet.next()) {
            ids.add(resultSet.getString("customerId"));
        }
        return ids;
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}

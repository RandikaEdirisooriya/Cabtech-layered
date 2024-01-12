package lk.ijse.CABTEACK.BO.Custom.Impl;

import lk.ijse.CABTEACK.BO.Custom.CustomerBO;
import lk.ijse.CABTEACK.DAO.Custom.CarsDao;
import lk.ijse.CABTEACK.DAO.Custom.CustomerDao;
import lk.ijse.CABTEACK.DAO.DAOFactory;
import lk.ijse.CABTEACK.DTO.Customer;
import lk.ijse.CABTEACK.ENTITY.CustomerEntity;
import lk.ijse.CABTEACK.ENTITY.VehicleEntity;

import java.sql.SQLException;
import java.util.List;

public class CustomerBoImpl implements CustomerBO {
    CustomerDao customerDao =
            (CustomerDao) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDao.getIds();
    }
/*    public CustomerEntity(String customerId, String name, String address, String nic_number, String contactNumber) {
 */
    @Override
    public boolean AddCustomer(Customer c) throws SQLException, ClassNotFoundException {
        return customerDao.save(new CustomerEntity(c.getCustomerId(), c.getName(), c.getAddress(), c.getNic_number(), c.getContactNumber()));
    }
}

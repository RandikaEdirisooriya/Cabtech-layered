package lk.ijse.CABTEACK.BO.Custom;

import lk.ijse.CABTEACK.BO.SuperBO;
import lk.ijse.CABTEACK.DTO.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    List<String> getCustomerIds() throws SQLException, ClassNotFoundException;
     boolean AddCustomer(Customer c) throws SQLException, ClassNotFoundException;
}

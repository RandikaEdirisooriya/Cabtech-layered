package lk.ijse.CABTEACK.BO.Custom;

import lk.ijse.CABTEACK.BO.SuperBO;
import lk.ijse.CABTEACK.DTO.Employee;
import lk.ijse.CABTEACK.DTO.vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    public ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException;
    List<String> getEmployeeIds() throws SQLException, ClassNotFoundException;
}

package lk.ijse.CABTEACK.BO.Custom.Impl;

import lk.ijse.CABTEACK.BO.Custom.EmployeeBO;
import lk.ijse.CABTEACK.DAO.Custom.CarsDao;
import lk.ijse.CABTEACK.DAO.Custom.EmployeeDao;
import lk.ijse.CABTEACK.DAO.DAOFactory;
import lk.ijse.CABTEACK.DTO.Employee;
import lk.ijse.CABTEACK.DTO.vehicle;
import lk.ijse.CABTEACK.ENTITY.EmployeeEntity;
import lk.ijse.CABTEACK.ENTITY.VehicleEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDao employeeDao =
            (EmployeeDao) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeEntity> empl = employeeDao.getAll();
        ArrayList<Employee> employees = new ArrayList<>();
/*    String empId, String name, String address, String nic_number, String contactNumber, String jobRole, double salary, ImageView img, String status) {
 */
        for (EmployeeEntity EmployeeEntity : empl) {
            Employee employee = new Employee(
                    EmployeeEntity.getEmpId(),
                    EmployeeEntity.getName(),
                    EmployeeEntity.getAddress(),
                    EmployeeEntity.getNic_number(),
                    EmployeeEntity.getContactNumber(),
                    EmployeeEntity.getJobRole() ,
                    EmployeeEntity.getSalary(),
                    EmployeeEntity.getImg(),
                    EmployeeEntity.getStatus()
            );
            employees.add(employee);
        }

        return employees;
    }

    @Override
    public List<String> getEmployeeIds() throws SQLException, ClassNotFoundException {
        return employeeDao.getIds();
    }
}

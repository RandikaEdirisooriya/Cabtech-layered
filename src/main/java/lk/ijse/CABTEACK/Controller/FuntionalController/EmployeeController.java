package lk.ijse.CABTEACK.Controller.FuntionalController;

import lk.ijse.CABTEACK.DAO.SqlUtil;
import lk.ijse.CABTEACK.DB.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController {
    public int getAllEmployee() throws SQLException, ClassNotFoundException {
/*        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql="SELECT COUNT(EmpId) as EmpId FROM employee";
        PreparedStatement pstm=connection.prepareStatement(sql);*/
        ResultSet resultSet = SqlUtil.execute("SELECT COUNT(EmpId) as EmpId FROM employee");
        int empId=0;
        while (resultSet.next()){
            empId=resultSet.getInt("EmpId");
        }
        return empId;
    }

}

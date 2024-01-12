package lk.ijse.CABTEACK.Controller;

import javafx.event.ActionEvent;
import lk.ijse.CABTEACK.DAO.SqlUtil;
import lk.ijse.CABTEACK.DB.DatabaseConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportFormController {
    public void PartReportsOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
      /*  Connection connection  = DatabaseConnection.getInstance().getConnection();
        String query = "SELECT * FROM vehiclepartsorder;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);*/

        ResultSet resultSet = SqlUtil.execute("SELECT * FROM vehiclepartsorder;");
        JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
        JasperDesign jasperDesign = JRXmlLoader.load(this.getClass().getResourceAsStream("/Report/vehiclePartOrder.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, resultSetDataSource);
        net.sf.jasperreports.view.JasperViewer.viewReport(jasperPrint, false);
    }

    public void VehicleOrderReprotOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, JRException {
       /* Connection connection  = DatabaseConnection.getInstance().getConnection();
        String query = "SELECT * FROM vehicleorder;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);*/
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM vehicleorder;");
        JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
        JasperDesign jasperDesign = JRXmlLoader.load(this.getClass().getResourceAsStream("/Report/vehicleOrder.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, resultSetDataSource);
        net.sf.jasperreports.view.JasperViewer.viewReport(jasperPrint, false);
    }

    public void VehicleOrderDetailsOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, JRException {
      /*  Connection connection = DatabaseConnection.getInstance().getConnection();
        String query = "SELECT * FROM vehicleorderdetails;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);*/
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM vehicleorderdetails;");
        JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
        JasperDesign jasperDesign = JRXmlLoader.load(this.getClass().getResourceAsStream("/Report/vehicleOrderDetails.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, resultSetDataSource);
        net.sf.jasperreports.view.JasperViewer.viewReport(jasperPrint, false);
    }

    public void VehiclePartsOrderDetailsOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, JRException {
        /*Connection connection = DatabaseConnection.getInstance().getConnection();
        String query = "SELECT * FROM vehiclepartsorderdetails;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);*/
        ResultSet resultSet = SqlUtil.execute("SELECT * FROM vehiclepartsorderdetails;");
        JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
        JasperDesign jasperDesign = JRXmlLoader.load(this.getClass().getResourceAsStream("/Report/VehiclepartsOrderDetails.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, resultSetDataSource);
        net.sf.jasperreports.view.JasperViewer.viewReport(jasperPrint, false);
    }
}

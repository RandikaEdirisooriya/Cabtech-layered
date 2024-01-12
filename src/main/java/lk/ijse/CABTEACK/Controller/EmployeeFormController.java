package lk.ijse.CABTEACK.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.CABTEACK.BO.Custom.EmployeeBO;
import lk.ijse.CABTEACK.BO.Custom.Impl.EmployeeBOImpl;
import lk.ijse.CABTEACK.DAO.SqlUtil;
import lk.ijse.CABTEACK.DTO.Employee;
import lk.ijse.CABTEACK.DTO.vehicle;
import lk.ijse.CABTEACK.TM.EmployeeTm;
import lk.ijse.CABTEACK.Controller.FuntionalController.EmployeeController;
import lk.ijse.CABTEACK.TM.VehicleTm;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class EmployeeFormController {

    public Label emplcountLbl;
    public Label datelbl;
    public Label timelbl;
    public JFXButton btnattndnce;

    @FXML
    private TableView<EmployeeTm> TableEmployee;

    @FXML
    private TableColumn<EmployeeTm, String> colEmpAction;

    @FXML
    private TableColumn<EmployeeTm, String> colEmpId;

    @FXML
    private TableColumn<EmployeeTm, String> colEmpImg;

    @FXML
    private TableColumn<EmployeeTm, String> colEmpName;

    @FXML
    private TableColumn<EmployeeTm, String> colEmpAddress;

    @FXML
    private TableColumn<?,?> colEmpNic;

    @FXML
    private TableColumn<EmployeeTm, String> colEmpContactNumer;

    @FXML
    private TableColumn<EmployeeTm, String> colEmpJobRole;

    @FXML
    private TableColumn<EmployeeTm, String> colEmpStatus;

    @FXML
    private TableColumn<EmployeeTm, Double> colEmpsalary;
    EmployeeBO employeeBO=new EmployeeBOImpl();

    private EmployeeController model=new EmployeeController();
    @FXML
    private Label lblNight;
    public void initialize() throws SQLException, ClassNotFoundException {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colEmpImg.setCellValueFactory(new PropertyValueFactory<>("img"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmpNic.setCellValueFactory(new PropertyValueFactory<>("nic_number"));
        colEmpContactNumer.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colEmpJobRole.setCellValueFactory(new PropertyValueFactory<>("jobRole"));
        colEmpStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colEmpsalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        setEmp();
        // Add update and delete buttons to the action column
        colEmpAction.setCellFactory(param -> new ActionButtonTableCell());

        loadAllEmployee();
        loadDateAndTime();
        TableEmployee.refresh();
    }

    private void loadDateAndTime() {
        java.util.Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        datelbl.setText(format.format(date));
        Timeline time=new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime=LocalTime.now();
            timelbl.setText(
                    currentTime.getHour()+":"+currentTime.getMinute()+":"+currentTime.getSecond()
            );
        }),new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    private void loadAllEmployee() throws SQLException, ClassNotFoundException {

        TableEmployee .getItems().clear();
        ArrayList<Employee> allEmpl = employeeBO.getAllEmployee();
        for (Employee EmployeeEntity : allEmpl) {
            TableEmployee.getItems().add(
                    new EmployeeTm(
                            EmployeeEntity.getEmpId(),
                            EmployeeEntity.getName(),
                            EmployeeEntity.getAddress(),
                            EmployeeEntity.getNic_number(),
                            EmployeeEntity.getContactNumber(),
                            EmployeeEntity.getJobRole() ,
                            EmployeeEntity.getSalary(),
                            EmployeeEntity.getImg(),
                            EmployeeEntity.getStatus()));

        }
    }

    public void AddNewEmployeeOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        addingEmployeeForm();
        loadAllEmployee();
    }

    private void addingEmployeeForm() throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/AddEmployee_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setTitle("Add Employee Form");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void btnattndnceOnAction(ActionEvent actionEvent) {
    }

    private class ActionButtonTableCell extends TableCell<EmployeeTm, String> {

        private final Button deleteButton = new Button("Delete");


        {
            deleteButton.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");

            deleteButton.setOnAction(event -> {
                EmployeeTm employee = getTableView().getItems().get(getIndex());
                try {
                    deleteEmployee(employee);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(new HBox(deleteButton));
            }
        }
    }


    private void deleteEmployee(EmployeeTm employee) throws SQLException, ClassNotFoundException {


        // Display confirmation dialog
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete Employee");
        confirm.setHeaderText("Are you sure you want to delete?");
        confirm.setContentText("ID: " + employee.getEmpId() + "\nName: " + employee.getName());

        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // If the user clicks OK, proceed with deletion
            String sql = "DELETE FROM employee WHERE empId=?";

            boolean g =SqlUtil.execute(sql, employee.getEmpId());


                if (g) {
                    // Deletion successful
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Employee Deleted");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee ID: " + employee.getEmpId() + " deleted successfully.");
                    alert.showAndWait();
                    ImageView imageView = new ImageView(new Image("/Asset/icons8-ok-48.png"));
                    Notifications.create()
                            .graphic(imageView)
                            .text(" Employee Deleted Successful ")
                            .title("Successful")
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.TOP_RIGHT)
                            .darkStyle()
                            .show();
                    // Refresh the table after deletion
                    loadAllEmployee();
                } else {
                    // No rows affected, deletion unsuccessful
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Deletion Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to delete employee. Please try again.");
                    alert.showAndWait();
                }
            }
        }

    private void setEmp() throws SQLException, ClassNotFoundException {
        int allemp = model.getAllEmployee();
        emplcountLbl.setText(String.valueOf(allemp));
        lblNight.setText(String.valueOf(allemp));
    }
}

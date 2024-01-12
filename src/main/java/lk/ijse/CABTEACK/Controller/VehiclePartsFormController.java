package lk.ijse.CABTEACK.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lk.ijse.CABTEACK.BO.Custom.Impl.VehiclePartsBOImpl;
import lk.ijse.CABTEACK.BO.Custom.VehiclepartBo;
import lk.ijse.CABTEACK.DAO.SqlUtil;
import lk.ijse.CABTEACK.DTO.VehicleParts;
import lk.ijse.CABTEACK.TM.VehiclePartsTm;
import lk.ijse.CABTEACK.Controller.FuntionalController.VehiclePartsController;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehiclePartsFormController {
    public JFXButton placeOrder;

    public AnchorPane DashAnchor;
    public Label lblItems;
    @FXML
    private JFXTextField Searchtxt;

    @FXML
    private JFXButton addNewParts;

    @FXML
    private TableColumn colAction;

    @FXML
    private TableColumn<VehiclePartsTm, String> colCode;

    @FXML
    private TableColumn<VehiclePartsTm, String> colColour;

    @FXML
    private TableColumn<VehiclePartsTm, String> colDescription;

    @FXML
    private TableColumn<VehiclePartsTm, String> colId;

    @FXML
    private TableColumn<VehiclePartsTm, String> colName;

    @FXML
    private TableColumn<VehiclePartsTm, String> colPart;

    @FXML
    private TableColumn<VehiclePartsTm, Integer> colQty;

    @FXML
    private TableView<VehiclePartsTm> tblparts;

    VehiclepartBo vehiclepartBo=new VehiclePartsBOImpl();
    private VehiclePartsController model=new VehiclePartsController();
    public void initialize() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("partsId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("nameOfVehiclePart"));
        colPart.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colColour.setCellValueFactory(new PropertyValueFactory<>("colour"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAction.setCellFactory(param -> new VehiclePartsFormController.ActionButtonTableCell());
        loadAllParts();
        tblparts.refresh();
setItems();
        loadVehicpartleId();
    }

    private void setItems() throws SQLException, ClassNotFoundException {
        int allItem = model.getAllItem();
        lblItems.setText(String.valueOf(allItem));

    }

    private void loadVehicpartleId() throws SQLException, ClassNotFoundException {
        List<String> cusIds = vehiclepartBo.getVehicleIdsIds();
        TextFields.bindAutoCompletion(Searchtxt,cusIds);
    }



    private void loadAllParts() throws SQLException, ClassNotFoundException {


        ArrayList<VehicleParts> allPart = vehiclepartBo.getAllVehiclePart();
        for (VehicleParts dto : allPart) {
            tblparts.getItems().add(
                    new VehiclePartsTm(
                            dto.getPartsId(),
                            dto.getQty(),
                            dto.getCode(),
                            dto.getColour(),
                            dto.getDescription(),
                            dto.getNameOfVehiclePart(),
                            dto.getPrice()));
        }
    }





    @FXML
    void addNewPartOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        addingVehiclePrtsForm();
        loadAllParts();
    }
    private void addingVehiclePrtsForm() throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/AddParts_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setTitle("Add Employee Form");
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void SearchPart(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        String searchText = Searchtxt.getText().trim();
        String sql;

        if (!searchText.isEmpty()) {
            // If an ID is selected, filter the data
            sql = "SELECT * FROM vehicleparts WHERE partsId = ?";
        } else {
            // If no ID is selected, retrieve all data
            sql = "SELECT * FROM vehicleparts";
        }

        ResultSet res = null;
        if (!searchText.isEmpty()) {
            // Set the parameter value before executing the query
            res = SqlUtil.execute(sql, searchText);
        } else {
            res = SqlUtil.execute(sql);
        }


       {
                ObservableList<VehiclePartsTm> obList = FXCollections.observableArrayList();
                while (res.next()) {
                    obList.add(new VehiclePartsTm(
                            res.getString(1),
                            res.getInt(2),
                            res.getString(3),
                            res.getString(4),
                            res.getString(5),
                            res.getString(6),
                            res.getDouble(7)
                    ));
                }
                tblparts.setItems(obList);
            }
        }


    public void placeOrderOnAction(ActionEvent actionEvent) throws IOException {

            Node node = FXMLLoader.load(getClass().getResource("/view/PlaceVehiclePart_form.fxml"));
            DashAnchor.getChildren().clear();
            DashAnchor.getChildren().setAll(node);


    }


    private class ActionButtonTableCell extends TableCell<VehiclePartsTm, String> {

        private final Button deleteButton = new Button("Delete");


        {
            deleteButton.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");


            deleteButton.setOnAction(event -> {
                VehiclePartsTm part = getTableView().getItems().get(getIndex());
                try {
                    deleteVehiclePart(part);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(new HBox( deleteButton));
            }
        }
    }



    private void deleteVehiclePart(VehiclePartsTm part) throws SQLException, ClassNotFoundException {


        // Display confirmation dialog
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete part");
        confirm.setHeaderText("Are you sure you want to delete?");
        confirm.setContentText("ID: " + part.getPartsId() + "\nName: " + part.getNameOfVehiclePart());

        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // If the user clicks OK, proceed with deletion
            String sql = "DELETE FROM vehicleparts WHERE partsId=?";
            boolean g =SqlUtil.execute(sql,part.getPartsId());


                if (g) {
                    // Deletion successful
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Part Deleted");
                    alert.setHeaderText(null);
                    alert.setContentText("Part ID: " + part.getPartsId() + " deleted successfully.");
                    alert.showAndWait();

                    // Refresh the table after deletion
                    loadAllParts();
                } else {
                    // No rows affected, deletion unsuccessful
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Deletion Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to delete Part. Please try again.");
                    alert.showAndWait();
                }
            }
        }
    }


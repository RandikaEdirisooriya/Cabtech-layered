package lk.ijse.CABTEACK.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import lk.ijse.CABTEACK.BO.Custom.Impl.ThreeWeelBoImpl;
import lk.ijse.CABTEACK.BO.Custom.ThreeWeelBo;
import lk.ijse.CABTEACK.DAO.SqlUtil;
import lk.ijse.CABTEACK.DB.DatabaseConnection;
import lk.ijse.CABTEACK.TM.VehicleTm;
import lk.ijse.CABTEACK.DTO.vehicle;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ThreeWheelsFormController {
    public JFXButton btnBike;
    public JFXButton bntCar;
    public AnchorPane Anchor;
    @FXML
    private JFXButton AddVehicleTBttn;

    @FXML
    private TableView<VehicleTm> CarsTble;

    @FXML
    private TableColumn<?, ?> ColDescription;

    @FXML
    private JFXButton PlaceOrderTBtn;

    @FXML
    private JFXTextField SearchIdsT;

    @FXML
    private TableColumn<VehicleTm, String> colAction;

    @FXML
    private TableColumn<?, ?> colColour;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private JFXTextField idTxt;

    @FXML
    private JFXTextField nametxt;

    @FXML
    private JFXTextField txtColur;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtdescrip;

    @FXML
    private JFXButton updateTVehiclebtn;

    ThreeWeelBo threeWeelBo =new ThreeWeelBoImpl();
    public void initialize() throws SQLException, ClassNotFoundException {
        colId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colName.setCellValueFactory(new PropertyValueFactory<>("nameOfVehicle"));
        colColour.setCellValueFactory(new PropertyValueFactory<>("colour"));
        ColDescription.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colAction.setCellFactory(param -> new ThreeWheelsFormController.ActionButtonTableCell());
        txtType.setText("Three Wheel");
        loadAllVehicle();
        CarsTble.refresh();
        loadVehicId();
        loadVehicleId();
    }

    private void loadVehicId() throws SQLException, ClassNotFoundException {
        List<String> vehiId = threeWeelBo.getVehicleIds();
        TextFields.bindAutoCompletion(SearchIdsT,vehiId);
    }
    private void loadVehicleId() throws SQLException, ClassNotFoundException {
        List<String> pId = threeWeelBo.getVehicleAllIds();
        TextFields.bindAutoCompletion(idTxt, pId);
    }
    private void loadAllVehicle() throws SQLException, ClassNotFoundException {
        CarsTble.getItems().clear();
        ArrayList<vehicle> allWeel = threeWeelBo.getAllVehicle();
        for (vehicle dto:allWeel) {
            CarsTble.getItems().add(
                    new VehicleTm(
                            dto.getVehicleId(),
                            dto.getVehicleType(),
                            dto.getNameOfVehicle(),
                            dto.getColour(),
                            dto.getPrice(),
                            dto.getQty()));

        }


    }

    @FXML
    void AddVehiceTBtnOnActon(ActionEvent event) throws SQLException, ClassNotFoundException {
       try {


        if (!idTxt.getText().isBlank() && !txtQty.getText().isBlank() && !txtType.getText().isBlank() &&
                !nametxt.getText().isBlank() && !txtColur.getText().isBlank() && !txtdescrip.getText().isBlank()) {
            if (validateInput()) {

                String vehicleId = idTxt.getText();
                String vehicleType = txtType.getText();
                String nameOfVehicle = nametxt.getText();
                String colour = txtColur.getText();
                double price = Double.parseDouble(String.valueOf(txtdescrip.getText()));
                int qty = Integer.parseInt(String.valueOf(txtQty.getText()));

                vehicle v1 = new vehicle(vehicleId, vehicleType, nameOfVehicle, colour, price, qty);

                if (AddVehicle(v1)) {
                    ImageView imageView = new ImageView(new Image("/Asset/icons8-ok-48.png"));
                    Notifications.create()
                            .graphic(imageView)
                            .text(" Vehicle Added Successful ")
                            .title("Successful")
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.TOP_RIGHT)
                            .darkStyle()
                            .show();
                } else {
                    ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
                    Notifications.create()
                            .graphic(imageView)
                            .text(" Vehicle Not Added. ")
                            .title("WARNING")
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.TOP_RIGHT)
                            .darkStyle()
                            .show();
                }
            } else {
                ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
                Notifications.create()
                        .graphic(imageView)
                        .text(" Vehicle Not Added. ")
                        .title("WARNING")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .darkStyle()
                        .show();
            }
        }else{
            ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
            Notifications.create()
                    .graphic(imageView)
                    .text(" Vehicle Not Added. ")
                    .title("WARNING")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .show();
        }
       }catch (SQLException | ClassNotFoundException e) {
           e.printStackTrace();
           ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
           Notifications.create()
                   .graphic(imageView)
                   .text("Vehicle Not Added. SQL Error: " + e.getMessage())
                   .title("WARNING")
                   .hideAfter(Duration.seconds(5))
                   .position(Pos.TOP_RIGHT)
                   .darkStyle()
                   .show();
       }
    }

    private boolean validateInput() {
        String empIdRegex = "V\\d{2}[A-Za-z0-9]+";
        String qtyRegex = "\\d+";


        if (!idTxt.getText().matches(empIdRegex)) {
            // Display warning notification for incorrect input
            ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
            Notifications.create()
                    .graphic(imageView)
                    .text("Invalid input. Please check your VehicleId.")
                    .title("WARNING")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .show();

            return false;
        }

        if (!txtQty.getText().matches(qtyRegex)) {
            // Display warning notification for incorrect input
            ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
            Notifications.create()
                    .graphic(imageView)
                    .text("Invalid input. Please check your QTY.")
                    .title("WARNING")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .show();

            return false;
        }



        // All input is valid
        return true;
    }

    private boolean AddVehicle(vehicle v1) throws SQLException, ClassNotFoundException {
        return threeWeelBo.AddVehicle(v1);
    }

    @FXML
    void PlaceOrdrTBtnOnAction(ActionEvent event) throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource("/view/PlaceVehicleOrder_form.fxml"));

        Anchor.getChildren().clear();
        Anchor.getChildren().setAll(node);
    }

    @FXML
    void SeacrchIdsTOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        searchtxtfeild();
        searchTble();
    }

    private void searchTble() throws SQLException, ClassNotFoundException {

        String searchText = SearchIdsT.getText().trim();
        String sql;

        if (!searchText.isEmpty()) {
            // If an ID is selected, filter the data
            sql = "SELECT * FROM vehicle WHERE vehicleId = ?";
        } else {
            // If no ID is selected, retrieve all data
            sql = "SELECT * FROM vehicle";
        }

        ResultSet res = null;
        if (!searchText.isEmpty()) {
            // Set the parameter value before executing the query
            res = SqlUtil.execute(sql, searchText);
        } else {
            res = SqlUtil.execute(sql);
        }



                ObservableList<VehicleTm> obList = FXCollections.observableArrayList();
                while (res.next()) {
                    obList.add(new VehicleTm(
                            res.getString(1), // Set ID
                            res.getString(3), // Set type
                            res.getString(4), // Set name
                            res.getString(5),// Set colour
                            res.getDouble(6),// Set description
                            res.getInt(2)
                    ));
                }
                CarsTble.setItems(obList);
            }



    private void searchtxtfeild() throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        String searchText = SearchIdsT.getText().trim();
        String sql;

        if (!searchText.isEmpty()) {
            // If an ID is selected, filter the data
            sql = "SELECT * FROM vehicle WHERE vehicleId = ?";
        } else {
            // If no ID is selected, retrieve all data
            sql = "SELECT * FROM vehicle";
        }

        ResultSet res = null;
        if (!searchText.isEmpty()) {
            // Set the parameter value before executing the query
            res = SqlUtil.execute(sql, searchText);
        } else {
            res = SqlUtil.execute(sql);
        }
                if (res.next()) {


                    idTxt.setText(res.getString(1)); // Set ID
                    txtType.setText(res.getString(3)); // Set type
                    nametxt.setText(res.getString(4)); // Set name
                    txtColur.setText(res.getString(5)); // Set colour


                    txtdescrip.setText(res.getString(6)); // Set description
                    txtQty.setText(String.valueOf(res.getInt(2))); // Set qty
                } else {
                    new Alert(Alert.AlertType.WARNING, "No matching records found.").show();
                    // Clear the text fields if no records are found
                    idTxt.clear();
                    txtType.clear();
                    nametxt.clear();
                    txtColur.clear();
                    txtdescrip.clear();
                    txtQty.clear();
                }
            }




    @FXML
    void updateVehiTbtnOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (!idTxt.getText().isBlank() && !txtQty.getText().isBlank() && !txtType.getText().isBlank() &&
                !nametxt.getText().isBlank() && !txtColur.getText().isBlank() && !txtdescrip.getText().isBlank()) {

            // If all fields are not blank, proceed with the update
            String tempId = idTxt.getText();
            int tempQty = Integer.parseInt(txtQty.getText());
            String tempvehicleType = txtType.getText();
            String tempnameOfVehicle = nametxt.getText();
            String tempcolour = txtColur.getText();
            double tempdescription = Double.parseDouble(String.valueOf(txtdescrip.getText()));

            vehicle c1 = new vehicle(tempId, tempvehicleType, tempnameOfVehicle, tempcolour, tempdescription, tempQty);

            if (threeWeelBo.updateVehicle(c1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated ::").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try again ::").show();
            }
        } else {
            // Show an alert if any of the fields are blank
            new Alert(Alert.AlertType.WARNING, "Please fill in all fields.").show();
        }

    }

    public void btnBikeOnAction(ActionEvent actionEvent) throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource("/view/BikeS_form.fxml"));

        Anchor.getChildren().clear();
        Anchor.getChildren().setAll(node);
    }

    public void bntCarOnAction(ActionEvent actionEvent) throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource("/view/CarS_form.fxml"));

        Anchor.getChildren().clear();
        Anchor.getChildren().setAll(node);
    }

    public class ActionButtonTableCell extends TableCell<VehicleTm, String> {

        private final Button deleteButton = new Button("Delete");


        {
            deleteButton.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");

            deleteButton.setOnAction(event -> {
                VehicleTm v = getTableView().getItems().get(getIndex());
                try {
                    deleteVehicle(v);
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
                setGraphic(new HBox(deleteButton));
            }
        }
    }




    private void deleteVehicle(VehicleTm v) throws SQLException, ClassNotFoundException {



        // Display confirmation dialog
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete Vehicle");
        confirm.setHeaderText("Are you sure you want to delete?");
        confirm.setContentText("ID: " + v.getVehicleId() + "\nName: " + v.getNameOfVehicle());

        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // If the user clicks OK, proceed with deletion
            String sql = "DELETE FROM vehicle WHERE vehicleId=?";

            boolean g =SqlUtil.execute(sql,v.getVehicleId());

                if (g) {
                    // Deletion successful
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Vehicle Deleted");
                    alert.setHeaderText(null);
                    alert.setContentText("vehicle ID: " + v.getVehicleId() + " deleted successfully.");
                    alert.showAndWait();

                    // Refresh the table after deletion
                    loadAllVehicle();
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
    }


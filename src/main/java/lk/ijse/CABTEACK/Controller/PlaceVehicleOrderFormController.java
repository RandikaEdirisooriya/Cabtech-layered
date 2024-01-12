package lk.ijse.CABTEACK.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.CABTEACK.BO.Custom.CarsBo;
import lk.ijse.CABTEACK.BO.Custom.CustomerBO;
import lk.ijse.CABTEACK.BO.Custom.Impl.CarBoImpl;
import lk.ijse.CABTEACK.BO.Custom.Impl.CustomerBoImpl;
import lk.ijse.CABTEACK.BO.Custom.Impl.PlaceVehicleOrderBOImpl;
import lk.ijse.CABTEACK.BO.Custom.Impl.VehicleOrderBOImpl;
import lk.ijse.CABTEACK.BO.Custom.PlaceVehicleOrderBo;
import lk.ijse.CABTEACK.BO.Custom.VehicleOrderBo;
import lk.ijse.CABTEACK.DAO.SqlUtil;
import lk.ijse.CABTEACK.TM.CartTmVehicle;
import lk.ijse.CABTEACK.DTO.VehicleOrderDetails;
import lk.ijse.CABTEACK.DTO.vehicleOrder;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PlaceVehicleOrderFormController {

    public JFXTextField OrderId;
    @FXML
    private Label AddCustomerLabel;

    @FXML
    private TableColumn<?, ?> ColPartName;

    @FXML
    private JFXTextField CustomerCN;

    @FXML
    private JFXTextField CustomerIdTxt;

    @FXML
    private JFXTextField CustomerName;

    @FXML
    private JFXTextField CustomerNiC;

    @FXML
    private JFXTextField PartNametxt;

    @FXML
    private JFXTextField Pricetxt;

    @FXML
    private JFXTextField QtyByCustomer;

    @FXML
    private JFXTextField QtyOnHand;

    @FXML
    private JFXButton add;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQtyByCustomer;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colVehicleId;

    @FXML
    private JFXTextField colourTxt;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblIdOrder;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<CartTmVehicle> tblOrder;

    @FXML
    private Label txtTotal;

    @FXML
    private JFXTextField vehicleIdTxt;
    int cartSelectedRow=-1;
    CarsBo carsBo =new CarBoImpl();
    CustomerBO customerBO=new CustomerBoImpl();
    VehicleOrderBo vehicleOrderBo=new VehicleOrderBOImpl();
    PlaceVehicleOrderBo placeVehicleOrderBo=new PlaceVehicleOrderBOImpl();

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        // colPartsId.setCellValueFactory(new PropertyValueFactory<>("PartId"));

        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("VehicleId"));
        ColPartName.setCellValueFactory(new PropertyValueFactory<>("NameOfParts"));
        colQtyByCustomer.setCellValueFactory(new PropertyValueFactory<>("qtyForCustomer"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));

        setOrderId();

        AddCustomerLabel.setVisible(false);

        // Add mouse entered event handler
        add.setOnMouseEntered(event -> {
            // Show the label when the mouse enters the button
            AddCustomerLabel.setVisible(true);
        });

        // Add mouse exited event handler
        add.setOnMouseExited(event -> {
            // Hide the label when the mouse exits the button
            AddCustomerLabel.setVisible(false);
        });
        loadCustomerId();
        loadVehicleId();
        loadDateAndTime();
        loadVehicleOrderId();
    }

    private void setOrderId() throws SQLException, ClassNotFoundException {

     //lblIdOrder.setText(new vehicleController().getOrderId());

    }

    ObservableList<CartTmVehicle> observableList = FXCollections.observableArrayList();
    @FXML
    void addButnOnAction(ActionEvent event) {


        String name = PartNametxt.getText();
        int qtyOnHand = Integer.parseInt(QtyOnHand.getText());
        double unitPrice = Double.parseDouble(Pricetxt.getText());
        int qtyForCustomer = Integer.parseInt(QtyByCustomer.getText());
        double Total = qtyForCustomer * unitPrice;

        if (qtyOnHand < qtyForCustomer) {
            ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
            Notifications.create()
                    .graphic(imageView)
                    .text("Incorrect quantity ")
                    .title("WARNING")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .show();
          //  new Alert(Alert.AlertType.WARNING, "Incorrect quantity").show();
            return;
        }

        CartTmVehicle tm = new CartTmVehicle(
                vehicleIdTxt.getText(),
                name,
                qtyOnHand,
                qtyForCustomer,
                unitPrice,
                Total
        );

        int rowIndex = isExist(tm);

        if (rowIndex == -1) {
            observableList.add(tm);
        } else {
            CartTmVehicle existingTm = observableList.get(rowIndex);
            int x = existingTm.getQtyForCustomer() + qtyForCustomer;
            if (x<=qtyOnHand) {
                CartTmVehicle newTm = new CartTmVehicle(
                        existingTm.getVehicleId(),
                        existingTm.getNameOfParts(),
                        existingTm.getQTY(),

                        existingTm.getQtyForCustomer() + qtyForCustomer,

                        unitPrice,
                        Total + existingTm.getTotal()
                );
                observableList.set(rowIndex, newTm);
            }else{
                ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
                Notifications.create()
                        .graphic(imageView)
                        .text("Incorrect quantity ")
                        .title("WARNING")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .darkStyle()
                        .show();
                // new Alert(Alert.AlertType.WARNING, "Incorrect quantity").show();
                return;
            }
        }

        tblOrder.setItems(observableList);
        calculateCost();
        tblOrder.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRow= (int) newValue;

        });

    }

    void calculateCost() {
        double ttl = 0;
        for (CartTmVehicle tm : observableList) {
            ttl += tm.getTotal();
        }
        txtTotal.setText(ttl + "/=");
    }

    private int isExist(CartTmVehicle tm) {
        for (int i = 0; i < observableList.size(); i++) {
            if (tm.getVehicleId().equals(observableList.get(i).getVehicleId())) {
                return i;
            }
        }
        return -1;
    }

    @FXML
    void PlaceOrderOnAction(ActionEvent event) {
        try {


            ArrayList<VehicleOrderDetails> item = new ArrayList<>();
            double total = 0;
            for (CartTmVehicle tempTm : observableList
            ) {
                total += tempTm.getTotal();
                item.add(
                        new VehicleOrderDetails(
                                tempTm.getVehicleId(),
                                tempTm.getUnitPrice(),
                                tempTm.getQtyForCustomer()
                        )
                );
            }

            vehicleOrder order = new vehicleOrder(
                    OrderId.getText(),
                    CustomerName.getText(),
                    CustomerIdTxt.getText(),

                    lblDate.getText(),
                    lblTime.getText(),
                    total,
                    item
            );

            if (placeVehicleOrderBo.placeOrder(order)) {
                callEmail();

            } else {
                ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
                Notifications.create()
                        .graphic(imageView)
                        .text(" Order Not Added. ")
                        .title("WARNING")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .darkStyle()
                        .show();

            }
        } catch (SQLException | ClassNotFoundException |IOException e) {
            e.printStackTrace();
            ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
            Notifications.create()
                    .graphic(imageView)
                    .text(" SQL Error: " + e.getMessage())
                    .title("WARNING")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .show();
        }
    }

        private void callEmail () throws IOException {
            Parent rootNode = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/email.fxml")));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setTitle("Add Customer Form");
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
        }

    @FXML
    void clearBtnOnAction(ActionEvent event) {

        
        if(cartSelectedRow==-1){

            ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
            Notifications.create()
                    .graphic(imageView)
                    .text("Please select the row! ")
                    .title("WARNING")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .show();
           // new Alert(Alert.AlertType.WARNING,"Please select the row").show();
        }else {

            observableList.remove(cartSelectedRow);
            calculateCost();
            tblOrder.refresh();
        }




    }




    private void loadDateAndTime() {
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));
        Timeline time=new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime=LocalTime.now();
            lblTime.setText(
                    currentTime.getHour()+":"+currentTime.getMinute()+":"+currentTime.getSecond()
            );
        }),new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }


    @FXML
    public void AddCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Customer_form.fxml")));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setTitle("Add Customer Form");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
    private void loadCustomerId() throws SQLException, ClassNotFoundException {
        List<String> cusId = customerBO.getCustomerIds();
        TextFields.bindAutoCompletion(CustomerIdTxt,cusId);
    }
    private void loadVehicleId() throws SQLException, ClassNotFoundException {
        List<String> pId = carsBo.getVehicleAllIds();
        TextFields.bindAutoCompletion(vehicleIdTxt,pId);
    }
    private void loadVehicleOrderId() throws SQLException, ClassNotFoundException {
        List<String> pId = vehicleOrderBo.getVehicleOrderAllIds();
        TextFields.bindAutoCompletion(OrderId,pId);
    }

    public void search(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        String searchText = CustomerIdTxt.getText().trim();
        String sql;

        if (!searchText.isEmpty()) {
            // If an ID is selected, filter the data
            sql = "SELECT * FROM customer WHERE customerId = ?";
        } else {
            // If no ID is selected, retrieve all data
            sql = "SELECT * FROM customer";
        }

        ResultSet res = null;
        if (!searchText.isEmpty()) {
            // Set the parameter value before executing the query
            res = SqlUtil.execute(sql, searchText);
        } else {
            res = SqlUtil.execute(sql);
        }

        if (res.next()) {


                    CustomerName.setText(res.getString(2));
                    CustomerCN.setText(res.getString(5));
                    CustomerNiC.setText(res.getString(4));




                } else {
                    new Alert(Alert.AlertType.WARNING, "No matching records found.").show();
                    // Clear the text fields if no records are found
                    CustomerName.clear();
                    CustomerCN.clear();
                    CustomerNiC.clear();



                }
            }





    public void partIdSearch(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String searchText = vehicleIdTxt.getText().trim();
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


                    colourTxt.setText(res.getString(5));
                    PartNametxt.setText(res.getString(4));
                    QtyOnHand.setText(res.getString(2));
                    Pricetxt.setText(res.getString(6));




                } else {
                    new Alert(Alert.AlertType.WARNING, "No matching records found.").show();
                    // Clear the text fields if no records are found
                    CustomerName.clear();
                    CustomerCN.clear();
                    CustomerNiC.clear();

                }
            }

}
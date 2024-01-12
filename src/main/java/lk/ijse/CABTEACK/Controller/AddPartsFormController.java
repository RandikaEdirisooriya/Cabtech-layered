package lk.ijse.CABTEACK.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import lk.ijse.CABTEACK.BO.Custom.Impl.VehiclePartsBOImpl;
import lk.ijse.CABTEACK.BO.Custom.VehiclepartBo;
import lk.ijse.CABTEACK.DTO.VehicleParts;
import lk.ijse.CABTEACK.Controller.FuntionalController.VehiclePartsController;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

public class AddPartsFormController {
    @FXML
    private JFXTextField codeTxt;
    public FileInputStream fileInputStream;
    public Image image;
    @FXML
    private JFXButton addimg;

    @FXML
    private JFXTextField colourtxt;

    @FXML
    private JFXTextField descriptionTxt;

    @FXML
    private JFXTextField nametxt;

    @FXML
    private JFXTextField partId;

    @FXML
    private JFXTextField partQty;

    @FXML
    private JFXTextField PRICETXT;

    VehiclepartBo vehiclepartBo=new VehiclePartsBOImpl();
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        loadPartId();
    }

    @FXML
    void addNewpartOnAction(ActionEvent event) {

        try {
            if (partId.getText().isBlank() == false && partQty.getText().isBlank() == false && codeTxt.getText().isBlank() == false && colourtxt.getText().isBlank() == false && descriptionTxt.getText().isBlank() == false && nametxt.getText().isBlank() == false && PRICETXT.getText().isBlank() == false) {

                if (validateInput()) {
                    String partIdt = partId.getText();
                    int partQtyt = Integer.parseInt(String.valueOf(partQty.getText()));
                    String codet = codeTxt.getText();
                    String colour = colourtxt.getText();
                    String descrip = descriptionTxt.getText();
                    String name = nametxt.getText();
                    double price = Double.parseDouble(String.valueOf(PRICETXT.getText()));


                    VehicleParts v = new VehicleParts(partIdt, partQtyt, codet, colour, descrip, name, price);

                    if (addNewPart(v)) {
                        ImageView imageView = new ImageView(new Image("/Asset/icons8-ok-48.png"));
                        Notifications.create()
                                .graphic(imageView)
                                .text(" Spare Part Added Successful ")
                                .title("Successful")
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.TOP_RIGHT)
                                .darkStyle()
                                .show();
                    } else {
                        ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
                        Notifications.create()
                                .graphic(imageView)
                                .text(" Spare Part Not Added. ")
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
                            .text(" Spare Part Not Added. ")
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
                        .text(" Spare Part Not Added. ")
                        .title("WARNING")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .darkStyle()
                        .show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
            Notifications.create()
                    .graphic(imageView)
                    .text(" Spare Part Not Added. SQL Error: " + e.getMessage())
                    .title("WARNING")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .show();
        }
    }

    private boolean addNewPart(VehicleParts v) throws SQLException, ClassNotFoundException {

        return  vehiclepartBo.AddVehicleParts(v);

    }

    private void loadPartId() throws SQLException, ClassNotFoundException {
        List<String> cusId = vehiclepartBo.getVehicleIdsIds();
        TextFields.bindAutoCompletion(partId, cusId);
    }

    private boolean validateInput() {
        String PartIdRegex = "V\\d{2}[A-Za-z0-9]+";
        String qtyRegex = "\\d+";

        if (!partId.getText().matches(PartIdRegex)) {
            // Display warning notification for incorrect input
            ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
            Notifications.create()
                    .graphic(imageView)
                    .text("Invalid input. Please check your PartId.")
                    .title("WARNING")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .show();

            return false;
        }
        if (!partQty.getText().matches(qtyRegex)) {
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
}



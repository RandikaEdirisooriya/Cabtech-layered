package lk.ijse.CABTEACK.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class VehicleFormController {
    public JFXButton BikeButton;
    public JFXButton ThreeWheelButton;
    public JFXButton carsButton;
    public AnchorPane vehicleAnchor;

    public void BikeButtonOnAction(ActionEvent actionEvent) throws IOException {
        if (vehicleAnchor != null) {
            Node node = FXMLLoader.load(getClass().getResource("/view/BikeS_form.fxml"));
            vehicleAnchor.getChildren().clear();
            vehicleAnchor.getChildren().setAll(node);
        } else {
            System.out.println("DshaAnchor is null. Check your FXML file.");
        }

    }

    public void ThreeWheelButtonOnAction(ActionEvent actionEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("/view/ThreeWheels_form.fxml"));
        vehicleAnchor.getChildren().clear();
        vehicleAnchor.getChildren().setAll(node);
    }

    public void carsButtonOnAction(ActionEvent actionEvent) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("/view/CarS_form.fxml"));
        vehicleAnchor.getChildren().clear();
        vehicleAnchor.getChildren().setAll(node);
    }
}

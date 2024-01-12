package lk.ijse.CABTEACK.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard2FormController implements Initializable {
    public AnchorPane anchorpane;
    public AnchorPane panel2;
    public ImageView menu;
    public AnchorPane dashBoardAnchorPane;
    public JFXButton dashbtn;
    public JFXButton btnProfoile;

    private boolean isPanel2Visible = false;
    private Timeline autoHideTimeline;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panel2.setVisible(false);
    }

    public void nextButtonOnAction(ActionEvent actionEvent) {
        if (isPanel2Visible) {
            // Create a FadeTransition to change opacity
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), panel2);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);

            // Create a TranslateTransition to slide out the panel
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), panel2);
            translateTransition.setToX(-panel2.getWidth());

            // Set up sequential animation
            fadeTransition.setOnFinished(event -> {
                panel2.setVisible(false);
            });
            fadeTransition.play();
            translateTransition.play();

            isPanel2Visible = false;

            // Cancel the auto-hide timeline if it's running
            if (autoHideTimeline != null) {
                autoHideTimeline.stop();
            }
        } else {
            // Create a FadeTransition to change opacity
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), panel2);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);

            // Create a TranslateTransition to slide in the panel
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), panel2);
            translateTransition.setToX(0);

            // Set up sequential animation
            panel2.setVisible(true);
            fadeTransition.play();
            translateTransition.play();

            isPanel2Visible = true;

            // Schedule the auto-hide timeline after 5 seconds
            if (autoHideTimeline != null) {
                autoHideTimeline.stop();
            }
            autoHideTimeline = new Timeline(new KeyFrame(Duration.seconds(7), e -> hidePanel2()));
            autoHideTimeline.setCycleCount(1);
            autoHideTimeline.play();
        }
    }

    private void hidePanel2() {
        if (isPanel2Visible) {
            // Create a FadeTransition to change opacity
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), panel2);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);

            // Create a TranslateTransition to slide out the panel
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), panel2);
            translateTransition.setToX(-panel2.getWidth());

            // Set up sequential animation
            fadeTransition.setOnFinished(event -> {
                panel2.setVisible(false);
                isPanel2Visible = false;
            });
            fadeTransition.play();
            translateTransition.play();
        }
    }

    public void setBtnColour(JFXButton btn) {


        dashbtn.getStyleClass().removeAll("select");
        dashbtn.getStyleClass().addAll("btn");
    }
    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource("/view/DashboardHome_form.fxml"));

        dashBoardAnchorPane.getChildren().setAll(node);
    }

    public void btnProfileOnAction(ActionEvent actionEvent) throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource("/view/Profile_form.fxml"));

        dashBoardAnchorPane.getChildren().clear();
        dashBoardAnchorPane.getChildren().setAll(node);
    }

    public void btnVehicleOnAction(ActionEvent actionEvent) throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource("/view/vehicle_form.fxml"));
        dashBoardAnchorPane.getChildren().clear();
        dashBoardAnchorPane.getChildren().setAll(node);
    }

    public void btnVehiclePartsOnAction(ActionEvent actionEvent) throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource("/view/vehicleParts_form.fxml"));
        dashBoardAnchorPane.getChildren().clear();
        dashBoardAnchorPane.getChildren().setAll(node);
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource("/view/employee_form.fxml"));

        dashBoardAnchorPane.getChildren().clear();
        dashBoardAnchorPane.getChildren().setAll(node);

    }

    public void btnReportsOnAction(ActionEvent actionEvent) throws IOException {
        Node node = (Node) FXMLLoader.load(getClass().getResource("/view/report_form.fxml"));
        dashBoardAnchorPane.getChildren().clear();
        dashBoardAnchorPane.getChildren().setAll(node);
    }


}

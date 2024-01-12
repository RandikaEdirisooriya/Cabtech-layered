package lk.ijse.CABTEACK.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginForm1Controller {
    public JFXButton loginbtn;

    public void loginbtnOnAction(ActionEvent actionEvent) throws IOException {
        createDashboard();
    }


        private void createDashboard() throws IOException {
            Parent rootNode = FXMLLoader.load(getClass().getResource("/view/Dashboard2_form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage primaryStage = (Stage) this.loginbtn.getScene().getWindow();
            primaryStage.getScene().getProperties().clear();
            primaryStage.setScene(scene);
            primaryStage.setTitle("");
        }

}

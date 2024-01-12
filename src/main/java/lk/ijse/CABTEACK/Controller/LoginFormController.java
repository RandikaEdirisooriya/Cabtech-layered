package lk.ijse.CABTEACK.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.CABTEACK.DB.DatabaseConnection;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    @FXML
    private Label loginsuccessfulMassage;
    @FXML
    private JFXButton loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userNameTextField;
    @FXML
    private Label loginMassage;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        if (userNameTextField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {
            validateLogin();
        } else {
            loginMassage.setText("Try again with the correct username and password");
        }
    }

    @FXML
    void initialize() {
        // Add an event handler to userNameTextField for Enter key press
        userNameTextField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                handleEnterKeyPressed();
            }
        });

        // Add an event handler to passwordField for Enter key press
        passwordField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                handleEnterKeyPressed();
            }
        });
    }

    private void handleEnterKeyPressed() {
        try {
            if (userNameTextField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {
                validateLogin();
            } else {
                loginMassage.setText("Try again with the correct username and password");
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }
    }

    public void validateLogin() throws SQLException, ClassNotFoundException, IOException {
        Connection con = DatabaseConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(2) FROM admin WHERE username = ? AND password = ?";

        PreparedStatement stm = con.prepareStatement(sql);

        stm.setObject(1, userNameTextField.getText());
        stm.setObject(2, passwordField.getText());
        ResultSet resultSet = stm.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            if (count > 0) {
                // Login successful
                loginsuccessfulMassage.setText("Login successful");

                loginMassage.setText("");
                createDashboard();
            } else {
                // Login failed
                loginMassage.setText("Try again with the correct username and password");

                new Alert(Alert.AlertType.WARNING, "Try again with the correct username and password").show();
            }
        }
    }

    private void createDashboard() throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/Dashboard2_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage primaryStage = (Stage) this.loginButton.getScene().getWindow();
        primaryStage.getScene().getProperties().clear();
        primaryStage.setScene(scene);
        primaryStage.setTitle("");
    }

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/Profile_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setTitle("");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

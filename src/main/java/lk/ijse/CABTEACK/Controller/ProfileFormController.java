package lk.ijse.CABTEACK.Controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import lk.ijse.CABTEACK.BO.Custom.BikeBo;
import lk.ijse.CABTEACK.BO.Custom.Impl.BikeBoImpl;
import lk.ijse.CABTEACK.BO.Custom.Impl.ProfileBOImpl;
import lk.ijse.CABTEACK.BO.Custom.ProfileBo;
import lk.ijse.CABTEACK.DAO.SqlUtil;

import lk.ijse.CABTEACK.DTO.admin;
import lk.ijse.CABTEACK.DTO.vehicle;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.util.List;

public class ProfileFormController {

    @FXML
    private JFXTextField emailTxt;

    @FXML
    private JFXTextField fullnametxt;

    @FXML
    private JFXPasswordField passwordtxt;

    @FXML
    private JFXTextField userIdTxt;

    ProfileBo profileBo =new ProfileBOImpl();
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        loadAdminId();
    }

    private void loadAdminId() throws SQLException, ClassNotFoundException {
        List<String> cusI = profileBo.getUserIds();
        TextFields.bindAutoCompletion(userIdTxt,cusI);
    }


    @FXML
    void createAccountOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String fullName = fullnametxt.getText();
        String userId = userIdTxt.getText();
        String password = passwordtxt.getText();
        String email = emailTxt.getText();
        admin v1 = new admin(userId,fullName,password,email);

        // Validation using regex
        if (!fullName.isBlank() && !userId.isBlank() && !password.isBlank() && !email.isBlank()) {
            // Validate email format
            if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
                Notifications.create()
                        .graphic(imageView)
                        .text(" Invalid email format. Please enter a valid email. ")
                        .title("WARNING")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .darkStyle()
                        .show();
                return;
            }

            if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
                ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
                Notifications.create()
                        .graphic(imageView)
                        .text("Invalid password format. Password must have at least 8 characters, one uppercase letter, one lowercase letter, and one digit. ")
                        .title("WARNING")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .darkStyle()
                        .show();
                return;
            }


//methandi v dagnna
            boolean g=profileBo.AddProfile(v1);

            if (g) {
                new Alert(Alert.AlertType.CONFIRMATION, "Created a user!!").show();
                fullnametxt.setText("");
                userIdTxt.setText("");
                passwordtxt.setText("");
                emailTxt.setText("");
            } else {
                ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
                Notifications.create()
                        .graphic(imageView)
                        .text(" Failed to create a user. Please try again. ")
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
                    .text(" Not created a user. Something is missing.. ")
                    .title("WARNING")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .show();
        }
    }
}
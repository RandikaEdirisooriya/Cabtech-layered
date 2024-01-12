package lk.ijse.CABTEACK.Controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import lk.ijse.CABTEACK.Controller.FuntionalController.emailmodel;
import org.controlsfx.control.Notifications;

import java.sql.SQLException;

public class EmailController {

    @FXML
    private JFXTextField fromtxt;

    @FXML
    private JFXTextArea massagetxtFeild;

    @FXML
    private JFXTextField subjecttxt;

    @FXML
    private JFXTextField totxt;
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        fromtxt.setText("randikaedirisooriya@gmail.com");
        subjecttxt.setText("Payment.");
    }
    @FXML
    void btnsendOnAction(ActionEvent event) {
        emailmodel gEmailSender = new emailmodel();
        // String to = "edirana07@gmail.com";
        // String from = "randikaedirisooriya@gmail.com";
        //  String subject = "First: Sending email using GMail";
        //  String text = "wade hari";
        if (validateInput()) {
            String to = totxt.getText();
            String from = fromtxt.getText();
            String subject = subjecttxt.getText();
            String text = massagetxtFeild.getText();

            boolean b = gEmailSender.sendEmail(to, from, subject, text);
            if (b) {
                ImageView imageView = new ImageView(new Image("/Asset/icons8-ok-48.png"));
                Notifications.create()
                        .graphic(imageView)
                        .text(" Transaction Successfully Completed!")
                        .title("Successful")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .darkStyle()
                        .show();

            } else {
                ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
                Notifications.create()
                        .graphic(imageView)
                        .text(" Transaction not Completed! ")
                        .title("WARNING")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .darkStyle()
                        .show();
            }
        }else {
            ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
            Notifications.create()
                    .graphic(imageView)
                    .text(" Transaction not Completed! ")
                    .title("WARNING")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .show();
        }
    }
    private boolean validateInput() {
        String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";


        if (!totxt.getText().matches(emailRegex)) {
            // Display warning notification for incorrect input
            ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
            Notifications.create()
                    .graphic(imageView)
                    .text("Invalid input. Please check your email.")
                    .title("WARNING")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .show();

            return false;
        }
        if (!fromtxt.getText().matches(emailRegex)) {
            // Display warning notification for incorrect input
            ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
            Notifications.create()
                    .graphic(imageView)
                    .text("Invalid input. Please check your email.")
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

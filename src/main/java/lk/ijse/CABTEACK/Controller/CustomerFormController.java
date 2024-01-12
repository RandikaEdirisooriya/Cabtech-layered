package lk.ijse.CABTEACK.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import lk.ijse.CABTEACK.BO.Custom.CustomerBO;
import lk.ijse.CABTEACK.BO.Custom.Impl.CustomerBoImpl;
import lk.ijse.CABTEACK.DTO.Customer;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {
    @FXML
    private JFXTextField cusAddress;

    @FXML
    private JFXTextField cusId;

    @FXML
    private JFXTextField cusName;

    @FXML
    private JFXTextField cusNic;

    @FXML
    private JFXTextField cusNum;
    CustomerBO customerBO=new CustomerBoImpl();

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        loadCustomerId();
    }


        private void loadCustomerId() throws SQLException, ClassNotFoundException {
            List<String> cusI = customerBO.getCustomerIds();
            TextFields.bindAutoCompletion(cusId,cusI);
        }


    @FXML
    void addOnAction(ActionEvent event)  {
       try {


        if (!cusId.getText().isBlank() && !cusAddress.getText().isBlank() && !cusName.getText().isBlank() &&
                !cusNic.getText().isBlank() && !cusNum.getText().isBlank() ) {
            if (validateInput()) {

                String custId = cusId.getText();
                String custAddress = cusAddress.getText();
                String custName = cusName.getText();
                String custNic = cusNic.getText();
                String custNum = cusNum.getText();


                Customer c = new Customer(custId, custName, custAddress, custNic, custNum);

                if (AddCustomer(c)) {
                    ImageView imageView = new ImageView(new Image("/Asset/icons8-ok-48.png"));
                    Notifications.create()
                            .graphic(imageView)
                            .text(" Customer Added Successful ")
                            .title("Successful")
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.TOP_RIGHT)
                            .darkStyle()
                            .show();
                } else {
                    ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
                    Notifications.create()
                            .graphic(imageView)
                            .text(" Customer Not Added. ")
                            .title("WARNING")
                            .hideAfter(Duration.seconds(5))
                            .position(Pos.TOP_RIGHT)
                            .darkStyle()
                            .show();
                }
            }
            } else {
                ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
                Notifications.create()
                        .graphic(imageView)
                        .text(" Please fill in all fields ")
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
                   .text(" Customer Not Added.. SQL Error: " + e.getMessage())
                   .title("WARNING")
                   .hideAfter(Duration.seconds(5))
                   .position(Pos.TOP_RIGHT)
                   .darkStyle()
                   .show();
       }


}

    private boolean validateInput() {
        String empIdRegex = "C\\d{2}[A-Za-z0-9]+";
        String nicRegex = "\\d{12}";
        String contactNumberRegex = "\\d{10}";


        if (!cusId.getText().matches(empIdRegex)) {
            // Display warning notification for incorrect input
            ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
            Notifications.create()
                    .graphic(imageView)
                    .text("Invalid input. Please check your CustomerId.")
                    .title("WARNING")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .show();

            return false;
        }


        if (!cusNic.getText().matches(nicRegex)) {
            // Display warning notification for incorrect input
            ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
            Notifications.create()
                    .graphic(imageView)
                    .text("Invalid input. Please check your NIC.")
                    .title("WARNING")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .show();

            return false;
        }

        if (!cusNum.getText().matches(contactNumberRegex)) {
            // Display warning notification for incorrect input
            ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
            Notifications.create()
                    .graphic(imageView)
                    .text("Invalid input. Please check your contact number.")
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

    private boolean AddCustomer(Customer c) throws SQLException, ClassNotFoundException {
        return customerBO.AddCustomer(c);
    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

}

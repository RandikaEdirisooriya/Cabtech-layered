package lk.ijse.CABTEACK.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import lk.ijse.CABTEACK.BO.Custom.EmployeeBO;
import lk.ijse.CABTEACK.BO.Custom.Impl.EmployeeBOImpl;
import lk.ijse.CABTEACK.DAO.SqlUtil;
import lk.ijse.CABTEACK.Controller.FuntionalController.EmployeeController;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class AddEmployeeFormController {

    public ImageView image_employee;
    public JFXTextField salaryTxt;
    @FXML
    private JFXTextField EmpAddress;

    @FXML
    private JFXTextField EmpNic;

    @FXML
    private JFXTextField empContactNumber;

    @FXML
    private JFXTextField empId;

    @FXML
    private JFXTextField empJobRole;

    @FXML
    private JFXTextField empName;

    public FileInputStream fileInputStream;
    public Image image;

    @FXML
    private JFXButton addimg;
    private File file;
    EmployeeBO employeeBO=new EmployeeBOImpl();

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        loadEmployeeId();
    }

    @FXML
    void addImg(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        file = chooser.showOpenDialog(addimg.getScene().getWindow());
        try {
            fileInputStream = new FileInputStream(file);
            image = new Image(fileInputStream);
            image_employee.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private byte[] imagenToByte(Image imagen) {
        BufferedImage bufferimage = SwingFXUtils.fromFXImage(imagen, null);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferimage, "jpg", output);
            ImageIO.write(bufferimage, "png", output);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] data = output.toByteArray();
        return data;
    }


    @FXML
    void addNewEmployeeOnAction(ActionEvent event) {
        try {
            // Your existing code...
           if( validateInput()) {
               boolean g = SqlUtil.execute("INSERT INTO employee (EmpId,name,address,nic_number,contactNumber,jobRole,salary,img,Status) " +
                       "VALUES (?,?,?,?,?,?,?,?,?)", empId.getText(), empName.getText(), EmpAddress.getText(), EmpNic.getText(), empContactNumber.getText(), empJobRole.getText(), salaryTxt.getText(), imagenToByte(image), "good");

               if (g) {
                   ImageView imageView = new ImageView(new Image("/Asset/icons8-ok-48.png"));
                   Notifications.create()
                           .graphic(imageView)
                           .text(" Employee Added Successful ")
                           .title("Successful")
                           .hideAfter(Duration.seconds(5))
                           .position(Pos.TOP_RIGHT)
                           .darkStyle()
                           .show();
               } else {
                   ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
                   Notifications.create()
                           .graphic(imageView)
                           .text(" Employee Not Added. ")
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
                       .text(" Employee Not Added. ")
                       .title("WARNING")
                       .hideAfter(Duration.seconds(5))
                       .position(Pos.TOP_RIGHT)
                       .darkStyle()
                       .show();

           }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Log the exception for debugging purposes

            // Show a warning notification for SQL error
            ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
            Notifications.create()
                    .graphic(imageView)
                    .text(" Employee Not Added. SQL Error: " + e.getMessage())
                    .title("WARNING")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .show();
         //  clearTxt();
        }
    }

    private void loadEmployeeId() throws SQLException, ClassNotFoundException {
        List<String> cusId = employeeBO.getEmployeeIds();
        TextFields.bindAutoCompletion(empId, cusId);
    }

    private void clearTxt() {
        empName.setText("");
        EmpAddress.setText("");
        EmpNic.setText("");
        empContactNumber.setText("");
        empJobRole.setText("");
        salaryTxt.setText("");
    }

    private boolean validateInput() {
        String empIdRegex = "E\\d{2}[A-Za-z0-9]+";
        String nicRegex = "\\d{12}";
        String contactNumberRegex = "\\d{10}";

        if (!empId.getText().matches(empIdRegex)) {
            // Display warning notification for incorrect input
            ImageView imageView = new ImageView(new Image("/Asset/icons8-close-100.png"));
            Notifications.create()
                    .graphic(imageView)
                    .text("Invalid input. Please check your employeeId.")
                    .title("WARNING")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                    .show();

            return false;
        }

        if (!EmpNic.getText().matches(nicRegex)) {
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

        if (!empContactNumber.getText().matches(contactNumberRegex)) {
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
}

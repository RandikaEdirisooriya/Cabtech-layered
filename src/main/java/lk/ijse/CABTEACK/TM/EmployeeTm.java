package lk.ijse.CABTEACK.TM;

import javafx.scene.image.ImageView;
import lombok.Data;



@Data

public class EmployeeTm {
    private String EmpId;
    private String name;
    private String address;
    private String nic_number;
    private String ContactNumber;
    private String jobRole;
    private double salary;
    private ImageView img;
    private String status;



    public EmployeeTm() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmpId='" + EmpId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic_number='" + nic_number + '\'' +
                ", ContactNumber='" + ContactNumber + '\'' +
                ", jobRole='" + jobRole + '\'' +
                ", salary=" + salary +
                ", img='" + img + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public EmployeeTm(String empId, String name, String address, String nic_number, String contactNumber, String jobRole, double salary, ImageView img, String status) {
        EmpId = empId;
        this.name = name;
        this.address = address;
        this.nic_number = nic_number;
        ContactNumber = contactNumber;
        this.jobRole = jobRole;
        this.salary = salary;
        this.img = img;
        this.status = status;
    }
}

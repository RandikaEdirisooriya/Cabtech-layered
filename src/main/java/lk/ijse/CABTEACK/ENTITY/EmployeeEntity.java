package lk.ijse.CABTEACK.ENTITY;

import javafx.scene.image.ImageView;

public class EmployeeEntity {
    private String EmpId;
    private String name;
    private String address;

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic_number() {
        return nic_number;
    }

    public void setNic_number(String nic_number) {
        this.nic_number = nic_number;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String nic_number;
    private String ContactNumber;
    private String jobRole;
    private double salary;
    private ImageView img;
    private String status;



    public EmployeeEntity() {
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

    public EmployeeEntity(String empId, String name, String address, String nic_number, String contactNumber, String jobRole, double salary, ImageView img, String status) {
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

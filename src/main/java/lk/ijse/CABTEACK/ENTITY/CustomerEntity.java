package lk.ijse.CABTEACK.ENTITY;

public class CustomerEntity {
    private String customerId;
    private String name;
    private String address;
    private String nic_number;
    private String contactNumber;

    public CustomerEntity() {
    }

    public CustomerEntity(String customerId, String name, String address, String nic_number, String contactNumber) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.nic_number = nic_number;
        this.contactNumber = contactNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic_number='" + nic_number + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}

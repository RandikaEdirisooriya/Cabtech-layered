package lk.ijse.CABTEACK.DTO;

public class vehicle {
    private String vehicleId;
    private String vehicleType;
    private String nameOfVehicle;
    private String colour;
    private double price;
    private int qty;

    public vehicle(String vehicleId, String vehicleType, String nameOfVehicle, String colour, double price, int qty) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.nameOfVehicle = nameOfVehicle;
        this.colour = colour;
        this.price = price;
        this.qty = qty;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getNameOfVehicle() {
        return nameOfVehicle;
    }

    public void setNameOfVehicle(String nameOfVehicle) {
        this.nameOfVehicle = nameOfVehicle;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "VehicleTm{" +
                "vehicleId='" + vehicleId + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", nameOfVehicle='" + nameOfVehicle + '\'' +
                ", colour='" + colour + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }

    public vehicle() {
    }
}

package lk.ijse.CABTEACK.TM;

public class VehiclePartsTm {
    private String partsId;
    private int qty;
    private String code;

    @Override
    public String toString() {
        return "VehiclePartsTm{" +
                "partsId='" + partsId + '\'' +
                ", qty=" + qty +
                ", code='" + code + '\'' +
                ", colour='" + colour + '\'' +
                ", description='" + description + '\'' +
                ", nameOfVehiclePart='" + nameOfVehiclePart + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public VehiclePartsTm() {
    }

    private String colour;
    private String description;

    public VehiclePartsTm(String partsId, int qty, String code, String colour, String description, String nameOfVehiclePart, double price) {
        this.partsId = partsId;
        this.qty = qty;
        this.code = code;
        this.colour = colour;
        this.description = description;
        this.nameOfVehiclePart = nameOfVehiclePart;
        this.price = price;
    }

    public String getPartsId() {
        return partsId;
    }

    public void setPartsId(String partsId) {
        this.partsId = partsId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameOfVehiclePart() {
        return nameOfVehiclePart;
    }

    public void setNameOfVehiclePart(String nameOfVehiclePart) {
        this.nameOfVehiclePart = nameOfVehiclePart;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private String nameOfVehiclePart;

    private double price;

}

package lk.ijse.CABTEACK.TM;

public class CartTmVehicle {
    private String VehicleId;

    public CartTmVehicle() {
    }

    @Override
    public String toString() {
        return "CartTmVehicle{" +
                "VehicleId='" + VehicleId + '\'' +
                ", NameOfParts='" + NameOfParts + '\'' +
                ", QTY=" + QTY +
                ", qtyForCustomer=" + qtyForCustomer +
                ", unitPrice=" + unitPrice +
                ", Total=" + Total +
                '}';
    }

    public String getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(String vehicleId) {
        VehicleId = vehicleId;
    }

    public String getNameOfParts() {
        return NameOfParts;
    }

    public void setNameOfParts(String nameOfParts) {
        NameOfParts = nameOfParts;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public int getQtyForCustomer() {
        return qtyForCustomer;
    }

    public void setQtyForCustomer(int qtyForCustomer) {
        this.qtyForCustomer = qtyForCustomer;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public CartTmVehicle(String vehicleId, String nameOfParts, int QTY, int qtyForCustomer, double unitPrice, double total) {
        VehicleId = vehicleId;
        NameOfParts = nameOfParts;
        this.QTY = QTY;
        this.qtyForCustomer = qtyForCustomer;
        this.unitPrice = unitPrice;
        Total = total;
    }

    private String NameOfParts;
    private int QTY;
    private int qtyForCustomer;
    private double unitPrice;
    private double Total;

}

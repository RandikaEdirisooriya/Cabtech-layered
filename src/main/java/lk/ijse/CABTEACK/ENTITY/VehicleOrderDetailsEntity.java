package lk.ijse.CABTEACK.ENTITY;

public class VehicleOrderDetailsEntity {
    private  String vehicleId;
    private  double  Amount;
    private  int  qtyForSale;

    public VehicleOrderDetailsEntity() {
    }

    public VehicleOrderDetailsEntity(String vehicleId, double amount, int qtyForSale) {
        this.vehicleId = vehicleId;
        Amount = amount;
        this.qtyForSale = qtyForSale;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public int getQtyForSale() {
        return qtyForSale;
    }

    @Override
    public String toString() {
        return "VehicleOrderDetails{" +
                "vehicleId='" + vehicleId + '\'' +
                ", Amount=" + Amount +
                ", qtyForSale=" + qtyForSale +
                '}';
    }

    public void setQtyForSale(int qtyForSale) {
        this.qtyForSale = qtyForSale;
    }
}

package lk.ijse.CABTEACK.DTO;

public class vehiclePartOrderDetail {
    private  String partId;
    private  double  Amount;
    private  int  qtyForSale;

    public vehiclePartOrderDetail() {
    }

    public vehiclePartOrderDetail(String partId, double amount, int qtyForSale) {
        this.partId = partId;
        Amount = amount;
        this.qtyForSale = qtyForSale;
    }

    @Override
    public String toString() {
        return "vehicleOrderDetailController{" +
                "partId='" + partId + '\'' +
                ", Amount=" + Amount +
                ", qtyForSale=" + qtyForSale +
                '}';
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
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

    public void setQtyForSale(int qtyForSale) {
        this.qtyForSale = qtyForSale;
    }


}

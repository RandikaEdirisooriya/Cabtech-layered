package lk.ijse.CABTEACK.TM;

public class CartTm {
    private String PartId;
    private String NameOfParts;
    private int QTY;
    private int qtyForCustomer;
    private double unitPrice;
    private double Total;

    public CartTm() {
    }

    public CartTm(String partId, String nameOfParts, int QTY, int qtyForCustomer, double unitPrice, double total) {
        PartId = partId;
        NameOfParts = nameOfParts;
        this.QTY = QTY;
        this.qtyForCustomer = qtyForCustomer;
        this.unitPrice = unitPrice;
        Total = total;
    }

    public String getPartId() {
        return PartId;
    }

    public void setPartId(String partId) {
        PartId = partId;
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

    @Override
    public String toString() {
        return "CartTm{" +
                "PartId='" + PartId + '\'' +
                ", NameOfParts='" + NameOfParts + '\'' +
                ", QTY=" + QTY +
                ", qtyForCustomer=" + qtyForCustomer +
                ", unitPrice=" + unitPrice +
                ", Total=" + Total +
                '}';
    }
}


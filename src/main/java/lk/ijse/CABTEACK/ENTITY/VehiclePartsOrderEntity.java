package lk.ijse.CABTEACK.ENTITY;

import lk.ijse.CABTEACK.DTO.vehiclePartOrderDetail;

import java.util.ArrayList;

public class VehiclePartsOrderEntity {
    private  String partOrderId;
    private  String customernName;
    private  String customerId;
    private  String orderDate;
    private  String orderTime;
    private  double cost;
    private ArrayList<vehiclePartOrderDetail> details;

    public VehiclePartsOrderEntity() {
    }

    public String getPartOrderId() {
        return partOrderId;
    }

    @Override
    public String toString() {
        return "vehiclePartsOrderController{" +
                "partOrderId='" + partOrderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", cost=" + cost +
                ", details=" + details +
                '}';
    }

    public void setPartOrderId(String partOrderId) {
        this.partOrderId = partOrderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<vehiclePartOrderDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<vehiclePartOrderDetail> details) {
        this.details = details;
    }

    public VehiclePartsOrderEntity(String partOrderId, String customerId, String orderDate, String orderTime, double cost, ArrayList<vehiclePartOrderDetail> details) {
        this.partOrderId = partOrderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.cost = cost;
        this.details = details;
    }



}

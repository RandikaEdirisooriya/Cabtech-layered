package lk.ijse.CABTEACK.DTO;

import java.util.ArrayList;

public class vehicleOrder {
    private  String VehicleOrderId;
    private  String customernName;
    private  String customerId;
    private  String orderDate;
    private  String orderTime;

    @Override
    public String toString() {
        return "vehicleOrder{" +
                "VehicleOrderId='" + VehicleOrderId + '\'' +
                ", customernName='" + customernName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", cost=" + cost +
                ", details=" + details +
                '}';
    }

    public String getVehicleOrderId() {
        return VehicleOrderId;
    }

    public void setVehicleOrderId(String vehicleOrderId) {
        VehicleOrderId = vehicleOrderId;
    }

    public String getCustomernName() {
        return customernName;
    }

    public void setCustomernName(String customernName) {
        this.customernName = customernName;
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

    public ArrayList<VehicleOrderDetails> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<VehicleOrderDetails> details) {
        this.details = details;
    }

    public vehicleOrder(String vehicleOrderId, String customernName, String customerId, String orderDate, String orderTime, double cost, ArrayList<VehicleOrderDetails> details) {
        VehicleOrderId = vehicleOrderId;
        this.customernName = customernName;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.cost = cost;
        this.details = details;
    }

    public vehicleOrder() {
    }

    private  double cost;
    private ArrayList<VehicleOrderDetails> details;
}

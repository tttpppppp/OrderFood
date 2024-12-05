package com.example.projecttest.model;

import java.time.LocalDateTime;

public class HistoryOrder {
    private long orderId;
    private String orderDate;
    private String restaurantName;
    private String address;
    private String username;
    private double totalPrice;

    public HistoryOrder(long orderId, String orderDate, String restaurantName, String address, String username, double totalPrice) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.restaurantName = restaurantName;
        this.address = address;
        this.username = username;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

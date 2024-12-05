package com.example.projecttest.model;

import java.util.List;

public class Order {
    private int userId;
    private int restaurantId;
    private Shipping shipping;
    private List<GioHang> foodOrderRequests;

    public Order(){

    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public List<GioHang> getFoodOrderRequests() {
        return foodOrderRequests;
    }

    public void setFoodOrderRequests(List<GioHang> foodOrderRequests) {
        this.foodOrderRequests = foodOrderRequests;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

}

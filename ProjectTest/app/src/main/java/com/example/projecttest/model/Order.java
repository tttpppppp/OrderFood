package com.example.projecttest.model;

import java.util.List;

public class Order {
    private int userId;
    private int restaurantId;
    private List<Food> listFood;

    public Order(int userId, int restaurantId, List<Food> listFood) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.listFood = listFood;
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

    public List<Food> getListFood() {
        return listFood;
    }

    public void setListFood(List<Food> listFood) {
        this.listFood = listFood;
    }
}

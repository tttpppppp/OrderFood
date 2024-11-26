package com.example.project_restaurant.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class KeyOrderDetails implements Serializable {
    public KeyOrderDetails() {

    }
    public KeyOrderDetails(int orderId, int foodId) {
        this.orderId = orderId;
        this.foodId = foodId;
    }

    @Column(name = "orderId")
    private int orderId;

    @Column(name = "foodId")
    private int foodId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}

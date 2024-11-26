package com.example.project_restaurant.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class KeyMenuRestaurant implements Serializable {
    public KeyMenuRestaurant() {

    }
    public KeyMenuRestaurant(int categoryId, int restaurantId) {
        this.categoryId = categoryId;
        this.restaurantId = restaurantId;
    }

    @Column(name = "categoryId")
    private int categoryId;

    @Column(name = "restaurantId")
    private int restaurantId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}

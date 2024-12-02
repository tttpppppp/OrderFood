package com.example.projecttest.model;

import java.io.Serializable;

public class Food implements Serializable {

    private int foodId;
    private String foodName;
    private String foodImage;
    private double foodPrice;
    private int isFreeShip;

    // Constructor
    public Food( int foodId, String foodName, String foodImage, int isFreeShip , double foodPrice) {
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.isFreeShip = isFreeShip;
        this.foodPrice = foodPrice;
        this.foodId = foodId;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    // Getter và Setter
    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public int getIsFreeShip() {
        return isFreeShip;
    }

    public void setIsFreeShip(int isFreeShip) {
        this.isFreeShip = isFreeShip;
    }

}

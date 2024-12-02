package com.example.project_restaurant.dto;

public class MenuDTO {
    private int foodId;
    private String foodName;
    private String foodImage;
    private double foodPrice;
    private int isFreeShip;

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

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setIsFreeShip(int isFreeShip) {
        this.isFreeShip = isFreeShip;
    }
}

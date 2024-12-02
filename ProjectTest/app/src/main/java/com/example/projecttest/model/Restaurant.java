package com.example.projecttest.model;

public class Restaurant {
    private int restaurantId;
    private int resourceImage;
    private String image;
    private String restaurantTitle;
    private String restaurantDes;
    private String restaurantSubTitle;
    private boolean isFreeShip;
    private String openDate;
    private String rating;
    private String categories;

    public Restaurant(String image , String restaurantTitle , int restaurantId){
        this.image = image;
        this.restaurantTitle = restaurantTitle;
        this.restaurantId = restaurantId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(int resourceImage) {
        resourceImage = resourceImage;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRestaurantTitle() {
        return restaurantTitle;
    }

    public void setRestaurantTitle(String restaurantTitle) {
        this.restaurantTitle = restaurantTitle;
    }

    public String getRestaurantDes() {
        return restaurantDes;
    }

    public void setRestaurantDes(String restaurantDes) {
        this.restaurantDes = restaurantDes;
    }

    public String getRestaurantSubTitle() {
        return restaurantSubTitle;
    }

    public void setRestaurantSubTitle(String restaurantSubTitle) {
        this.restaurantSubTitle = restaurantSubTitle;
    }

    public boolean isFreeShip() {
        return isFreeShip;
    }

    public void setFreeShip(boolean freeShip) {
        isFreeShip = freeShip;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}

package com.example.project_restaurant.dto;

import java.util.Date;
import java.util.List;

public class RestaurantDTO {
    private int restaurantId;
    private String image;
    private String restaurantTitle;
    private String restaurantDes;
    private String restaurantSubTitle;
    private int isFreeShip;
    private Date openDate;
    private double  rating;
    List<CategoryDTO> categories;


    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
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

    public int getIsFreeShip() {
        return isFreeShip;
    }

    public void setIsFreeShip(int isFreeShip) {
        this.isFreeShip = isFreeShip;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}


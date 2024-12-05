package com.example.project_restaurant.payload.request;

import com.example.project_restaurant.dto.MenuDTO;
import com.example.project_restaurant.dto.ShippingDTO;

import java.util.List;

public class OrderRequest {
    private int userId;
    private int restaurantId;
    private ShippingDTO shipping;
    private List<FoodOrderRequest> foodOrderRequests;

    public OrderRequest(int userId, int restaurantId, List<FoodOrderRequest> foodOrderRequests) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.foodOrderRequests = foodOrderRequests;
    }

    public ShippingDTO getShipping() {
        return shipping;
    }

    public void setShipping(ShippingDTO shipping) {
        this.shipping = shipping;
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

    public List<FoodOrderRequest> getFoodOrderRequests() {
        return foodOrderRequests;
    }

    public void setFoodOrderRequests(List<FoodOrderRequest> foodOrderRequests) {
        this.foodOrderRequests = foodOrderRequests;
    }
}

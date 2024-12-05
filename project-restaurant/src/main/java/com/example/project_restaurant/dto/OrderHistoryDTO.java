package com.example.project_restaurant.dto;

import com.example.project_restaurant.payload.request.FoodOrderRequest;

import java.util.Date;
import java.util.List;

public class OrderHistoryDTO {
    private int orderId;
    private int restaurantId;
    private Date orderDate;
    private List<MenuDTO> listMenus;

    public OrderHistoryDTO() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<MenuDTO> getListMenus() {
        return listMenus;
    }

    public void setListMenus(List<MenuDTO> listMenus) {
        this.listMenus = listMenus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}

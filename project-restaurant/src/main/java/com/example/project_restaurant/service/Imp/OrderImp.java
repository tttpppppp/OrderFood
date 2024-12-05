package com.example.project_restaurant.service.Imp;

import com.example.project_restaurant.dto.MenuDTO;
import com.example.project_restaurant.dto.OrderDTO;
import com.example.project_restaurant.dto.OrderHistoryDTO;
import com.example.project_restaurant.payload.request.FoodOrderRequest;
import com.example.project_restaurant.payload.request.OrderRequest;

import java.util.List;

public interface OrderImp {
    boolean orderFood(OrderRequest orderRequest);
    public List<OrderDTO> getAllOrders(Long userId);
    List<MenuDTO> getOrderDeatils(int orderId);
}

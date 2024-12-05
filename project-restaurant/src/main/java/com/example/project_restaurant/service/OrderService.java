package com.example.project_restaurant.service;

import com.example.project_restaurant.dto.MenuDTO;
import com.example.project_restaurant.dto.OrderDTO;
import com.example.project_restaurant.dto.OrderHistoryDTO;
import com.example.project_restaurant.entity.*;
import com.example.project_restaurant.entity.keys.KeyOrderDetails;
import com.example.project_restaurant.payload.request.FoodOrderRequest;
import com.example.project_restaurant.payload.request.OrderRequest;
import com.example.project_restaurant.repository.OrderDetailsRepository;
import com.example.project_restaurant.repository.OrderRepository;
import com.example.project_restaurant.repository.ShippingRepository;
import com.example.project_restaurant.service.Imp.OrderImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderService implements OrderImp {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private ShippingRepository shippingRepository;
    @Override
    @Transactional
    public boolean orderFood(OrderRequest orderRequest) {
        try {
            Shipping shipping = new Shipping();
            shipping.setUsername(orderRequest.getShipping().getUsername());
            shipping.setAddress(orderRequest.getShipping().getAddress());
            shipping.setMobile(orderRequest.getShipping().getMobile());
            shippingRepository.save(shipping);

            Users user = new Users();
            user.setUserId(orderRequest.getUserId());

            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantId(orderRequest.getRestaurantId());

            double totalPrice = (double) orderRequest.getFoodOrderRequests().stream()
                    .mapToDouble(FoodOrderRequest::getPrice)
                    .sum();

            Orders orders = new Orders();
            int idOrder = (int)(Math.random() * 9000) + 1000;
            orders.setOrderId(idOrder);
            orders.setUser(user);
            orders.setRestaurant(restaurant);
            orders.setOrderDate(new Date());
            orders.setShipping(shipping);
            orders.setTotal(totalPrice);
            orderRepository.save(orders);

            List<OrderDetails> orderDetails = new ArrayList<>();
            for (FoodOrderRequest foodOrderRequest : orderRequest.getFoodOrderRequests()) {
                OrderDetails orderDetail = new OrderDetails();
                Food food = new Food();
                food.setFoodId(foodOrderRequest.getProductId());
                KeyOrderDetails keyOrderDetails = new KeyOrderDetails(orders.getOrderId(), foodOrderRequest.getProductId());
                orderDetail.setKey(keyOrderDetails);
                orderDetails.add(orderDetail);
            }
            orderDetailsRepository.saveAll(orderDetails);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<OrderDTO> getAllOrders(Long userId) {
        List<Orders> orders = orderRepository.findByUser_UserId(userId);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Orders order : orders) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(order.getOrderId());
            orderDTO.setRestaurantName(order.getRestaurant().getRestaurantTitle());
            orderDTO.setAddress(order.getShipping().getAddress());
            orderDTO.setUsername(order.getShipping().getUsername());
            orderDTO.setTotalPrice(order.getTotal());
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

    @Override
    public List<MenuDTO> getOrderDeatils(int orderId) {
        Optional<Orders> orders = orderRepository.findById(orderId);
        List<MenuDTO> orderHistoryDTOList = new ArrayList<>();
        List<MenuDTO> menuDTOS = new ArrayList<>();
        if (orders.isPresent()) {
            for (OrderDetails details : orders.get().getOrderDetails()) {
                MenuDTO menuDTO = new MenuDTO();
                menuDTO.setFoodId(details.getFood().getFoodId());
                menuDTO.setFoodName(details.getFood().getFoodName());
                menuDTO.setFoodPrice(details.getFood().getFoodPrice());
                menuDTO.setFoodImage(details.getFood().getFoodImage());
                menuDTO.setIsFreeShip(details.getFood().getIsFreeShip());
                menuDTOS.add(menuDTO);
            }
        }
        return menuDTOS;
    }
}

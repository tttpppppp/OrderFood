package com.example.project_restaurant.controller;

import com.example.project_restaurant.dto.OrderHistoryDTO;
import com.example.project_restaurant.payload.ResponeData;
import com.example.project_restaurant.payload.request.OrderRequest;
import com.example.project_restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> orderFood(@RequestBody OrderRequest orderRequest) {
        ResponeData responeData = new ResponeData();
        try {
            boolean isSuccess = orderService.orderFood(orderRequest);
            if (isSuccess) {
                responeData.setMessage("Order success");
                return ResponseEntity.ok(responeData);
            }
            responeData.setMessage("Order failed");
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping("/getOrderDetails")
    public ResponseEntity<?> getHistoryOrder(@RequestParam int orderId) {
        ResponeData responeData = new ResponeData();
        try {
            if (!orderService.getOrderDeatils(orderId).isEmpty()) {
                responeData.setMessage("Get history success");
                responeData.setData(orderService.getOrderDeatils(orderId));
                return ResponseEntity.ok(responeData);
            }
            responeData.setMessage("Get history failed");
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping("/getAllOrder")
    public ResponseEntity<?> getAllOrders(Long userId) {
        ResponeData responeData = new ResponeData();
        try {
            if (!orderService.getAllOrders(userId).isEmpty()) {
                responeData.setMessage("Get success");
                responeData.setData(orderService.getAllOrders(userId));
                return ResponseEntity.ok(responeData);
            }
            responeData.setMessage("Get failed");
            return ResponseEntity.ok(responeData);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

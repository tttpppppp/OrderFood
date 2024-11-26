package com.example.project_restaurant.controller;

import com.example.project_restaurant.payload.ResponeData;
import com.example.project_restaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/menu")
public class MenuRestaurant {
    @Autowired
    private MenuService menuService;

    @PostMapping("/")
    public ResponseEntity<?> createMenu(
            @RequestParam MultipartFile file,
            @RequestParam String foodName,
            @RequestParam String time_ship,
            @RequestParam int isFreeShip,
            @RequestParam double foodPrice,
            @RequestParam int foodQuantity,
            @RequestParam int categoryId

    ) {
        ResponeData responeData = new ResponeData();
        boolean isSuccess = menuService.createMenu(file , foodName, time_ship, isFreeShip, foodPrice, foodQuantity, categoryId);
        if(isSuccess){
            responeData.setMessage("Create food successful");
            return new ResponseEntity<>(responeData , HttpStatus.OK);
        }
        responeData.setMessage("Create food failed");
        return new ResponseEntity<>(responeData , HttpStatus.OK);
    }
}

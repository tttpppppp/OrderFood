package com.example.project_restaurant.controller;

import com.example.project_restaurant.payload.ResponeData;
import com.example.project_restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/")
    public ResponseEntity<?> createRestaurant(
            @RequestParam MultipartFile file,
            @RequestParam String restaurantTitle,
            @RequestParam String restaurantSubTitle,
            @RequestParam String restaurantDesc,
            @RequestParam int isFreeShip,
            @RequestParam String address,
            @RequestParam String openDate
    ) {
        ResponeData responeData = new ResponeData();
        boolean isSuccess = restaurantService.createRestaurant(file , restaurantTitle, restaurantSubTitle , restaurantDesc , isFreeShip ,address , openDate);
        if(isSuccess){
            responeData.setMessage("Create restaurant successful");
            return new ResponseEntity<>(responeData , HttpStatus.OK);
        }
        responeData.setMessage("Create restaurant failed");
        return new ResponseEntity<>(responeData , HttpStatus.OK);
    }
    @GetMapping("/getHomePage")
    public ResponseEntity<?> getHomePage() {
        ResponeData responeData = new ResponeData();
        if(!restaurantService.getAllRestaurants().isEmpty()){
            responeData.setMessage("getHomePage successful");
            responeData.setData(restaurantService.getAllRestaurants());
            return new ResponseEntity<>(responeData , HttpStatus.OK);
        }
        responeData.setMessage("Chưa có dữ liệu");
        return new ResponseEntity<>(responeData , HttpStatus.OK);
    }
    @GetMapping("/details")
    public ResponseEntity<?> getRestaurant(@RequestParam int id) {
        ResponeData responeData = new ResponeData();
        if(restaurantService.getRestaurantById(id) != null){
            responeData.setMessage("Get details successful");
            responeData.setData(restaurantService.getRestaurantById(id));
            return new ResponseEntity<>(responeData , HttpStatus.OK);
        }
        responeData.setMessage("Chưa có dữ liệu");
        return new ResponseEntity<>(responeData , HttpStatus.OK);
    }
}

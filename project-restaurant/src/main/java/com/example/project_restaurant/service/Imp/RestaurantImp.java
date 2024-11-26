package com.example.project_restaurant.service.Imp;

import com.example.project_restaurant.dto.RestaurantDTO;
import com.example.project_restaurant.entity.Restaurant;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantImp {
    boolean createRestaurant( MultipartFile file,
                              String restaurantTitle, String restaurantSubTitle, String restaurantDesc,
                              int isFreeShip,
                              String address,
                              String openDate);
    List<RestaurantDTO> getAllRestaurants();
    RestaurantDTO getRestaurantById(int id);
}

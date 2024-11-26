package com.example.project_restaurant.service.Imp;

import org.springframework.web.multipart.MultipartFile;

public interface MenuImp {
    boolean createMenu(  MultipartFile file,
                         String foodName,
                         String time_ship,
                         int isFreeShip,
                         double foodPrice,
                         int foodQuantity,
                         int categoryId);
}

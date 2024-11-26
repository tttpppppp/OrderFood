package com.example.project_restaurant.service;

import com.example.project_restaurant.entity.Category;
import com.example.project_restaurant.entity.Food;
import com.example.project_restaurant.repository.FoodRepository;
import com.example.project_restaurant.service.Imp.MenuImp;
import com.example.project_restaurant.service.uploadfile.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class MenuService implements MenuImp {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FilesStorageService filesStorageService;


    @Override
    public boolean createMenu(MultipartFile file, String foodName, String time_ship, int isFreeShip, double foodPrice, int foodQuantity, int categoryId) {
        boolean isInsertSucess = false;
        try {
            boolean isSucess = filesStorageService.save(file);
            if(isSucess) {
                Food food = new Food();
                food.setFoodName(foodName);
                food.setFoodImage(file.getOriginalFilename());
                food.setFoodPrice(foodPrice);
                food.setFoodQuantity(foodQuantity);
                food.setIsFreeShip(isFreeShip);
                food.setTime_ship(time_ship);
                Category category = new Category();
                category.setCategoryId(categoryId);
                food.setCategory(category);
                foodRepository.save(food);
                isInsertSucess = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            isInsertSucess = false;
        }
        return isInsertSucess;
    }
}

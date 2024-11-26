package com.example.project_restaurant.controller;

import com.example.project_restaurant.payload.ResponeData;
import com.example.project_restaurant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<?> getAllCategory() {
        try {
            ResponeData responeData = new ResponeData();
            if(categoryService.getAllCategories().isEmpty()){
                responeData.setMessage("Không có danh mục nào!");
                return new ResponseEntity<>(responeData , HttpStatus.OK);
            }
            responeData.setMessage("Get successs!");
            responeData.setData(categoryService.getAllCategories());
            return new ResponseEntity<>(responeData , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>( e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

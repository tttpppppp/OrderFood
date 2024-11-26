package com.example.project_restaurant.service;

import com.example.project_restaurant.dto.MenuDTO;
import com.example.project_restaurant.dto.CategoryDTO;
import com.example.project_restaurant.entity.Category;
import com.example.project_restaurant.entity.Food;
import com.example.project_restaurant.repository.CategoryRepository;
import com.example.project_restaurant.service.Imp.Categoryimp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements Categoryimp {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categories = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<Category> listCategory = categoryRepository.findAll(pageRequest);
        for (Category category : listCategory) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setNameCategory(category.getCategoryName());
            List<MenuDTO> menuDTOs = new ArrayList<>();
            for(Food food : category.getFoods()) {
                MenuDTO menuDTO = new MenuDTO();
                menuDTO.setFoodId(food.getFoodId());
                menuDTO.setFoodName(food.getFoodName());
                menuDTO.setFoodImage(food.getFoodImage());
                menuDTO.setIsFreeShip(food.getIsFreeShip());
                menuDTOs.add(menuDTO);
            }
            categoryDTO.setMenuDTOList(menuDTOs);
            categories.add(categoryDTO);
        }
        return categories;
    }
}

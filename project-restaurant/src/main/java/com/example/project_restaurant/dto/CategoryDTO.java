package com.example.project_restaurant.dto;

import java.util.List;

public class CategoryDTO {
    private String nameCategory;
    private List<MenuDTO> menuDTOList;

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<MenuDTO> getMenuDTOList() {
        return menuDTOList;
    }

    public void setMenuDTOList(List<MenuDTO> menuDTOList) {
        this.menuDTOList = menuDTOList;
    }
}

package com.example.projecttest.model;

import java.util.List;

public class Category {

    private String nameCategory;
    private List<Food> menuDTOList;

    public Category(String nameCategory, List<Food> menuDTOList) {
        this.nameCategory = nameCategory;
        this.menuDTOList = menuDTOList;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<Food> getMenuDTOList() {
        return menuDTOList;
    }

    public void setMenuDTOList(List<Food> menuDTOList) {
        this.menuDTOList = menuDTOList;
    }
}

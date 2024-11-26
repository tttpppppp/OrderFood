package com.example.project_restaurant.entity;


import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name = "categoryName")
    private String categoryName;

    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(mappedBy = "category")
    private Set<Food> foods;

    @OneToMany(mappedBy = "category")
    private Set<MenuRestaurant> menuRestaurants;

    public Set<Food> getFoods() {
        return foods;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }

    public Set<MenuRestaurant> getMenuRestaurants() {
        return menuRestaurants;
    }

    public void setMenuRestaurants(Set<MenuRestaurant> menuRestaurants) {
        this.menuRestaurants = menuRestaurants;
    }
}

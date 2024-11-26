package com.example.project_restaurant.entity;

import com.example.project_restaurant.entity.keys.KeyMenuRestaurant;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "menu_restaurant")
public class MenuRestaurant {
    @EmbeddedId
    KeyMenuRestaurant key;

    @Column(name = "createdate")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "categoryId" , insertable = false, updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "restaurantId" , insertable = false, updatable = false)
    private Restaurant restaurant;

    public KeyMenuRestaurant getKey() {
        return key;
    }

    public void setKey(KeyMenuRestaurant key) {
        this.key = key;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}

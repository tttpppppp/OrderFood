package com.example.project_restaurant.entity;


import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodId;

    @Column(name = "foodName")
    private String foodName;

    @Column(name = "foodImage")
    private String foodImage;

    @Column(name = "timeShip")
    private String time_ship;

    @Column(name = "isFreeShip")
    private int isFreeShip;

    @Column(name = "foodPrice")
    private double foodPrice;

    @Column(name = "foodQuantity")
    private int foodQuantity;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "food")
    private Set<RatingFood> ratingFood;

    @OneToMany(mappedBy = "food")
    private Set<OrderDetails> orderDetails;

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getTime_ship() {
        return time_ship;
    }

    public void setTime_ship(String time_ship) {
        this.time_ship = time_ship;
    }

    public int getIsFreeShip() {
        return isFreeShip;
    }

    public void setIsFreeShip(int isFreeShip) {
        this.isFreeShip = isFreeShip;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<RatingFood> getRatingFood() {
        return ratingFood;
    }

    public void setRatingFood(Set<RatingFood> ratingFood) {
        this.ratingFood = ratingFood;
    }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}

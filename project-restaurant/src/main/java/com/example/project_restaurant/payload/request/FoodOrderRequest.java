package com.example.project_restaurant.payload.request;

public class FoodOrderRequest {
    private String imageUrl;
    private double price;
    private int productId;
    private String productName;
    private int quantity;

    // Constructor
    public FoodOrderRequest(String imageUrl, double price, int productId, String productName, int quantity) {
        this.imageUrl = imageUrl;
        this.price = price;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

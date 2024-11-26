package com.example.project_restaurant.entity;

import com.example.project_restaurant.entity.keys.KeyOrderDetails;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "order_details")
public class OrderDetails {
    @EmbeddedId
    KeyOrderDetails key;

    @Column(name = "createdate")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "orderId" , insertable = false, updatable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "foodId" , insertable = false, updatable = false)
    private Food food;

    public KeyOrderDetails getKey() {
        return key;
    }

    public void setKey(KeyOrderDetails key) {
        this.key = key;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}

package com.epam.donetc.restaurant.database.entity;

import java.util.Map;

public class Receipt  {
    private  int id;
    private  User user;
    private  Status status;
    private Map<Dish, Integer> dishes;
    private int total;

    private String address;

    public Receipt() {
      }

    public Receipt(int id, User user, Status status, Map<Dish, Integer> dishes, int total, String address) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.dishes = dishes;
        this.total = total;
        this.address = address;
    }

    public Receipt(int id, User user, Status status) {
        this.id = id;
        this.user = user;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Status getStatus() {
        return status;
    }

    public Map<Dish, Integer> getDishes() {
        return dishes;
    }

    public int getTotal() {
        return total;
    }

    public void setDishes(Map<Dish, Integer> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", user=" + user +
                ", status=" + status +
                ", dishes=" + dishes +
                '}';
    }

    public void countTotal() {
        total = 0;
        for (Dish d:
                dishes.keySet()) {
            total += (d.getPrice() * dishes.get(d));
        }
    }
}
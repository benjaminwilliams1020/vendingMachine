package com.techelevator;

import java.math.BigDecimal;

public abstract class Snack {

    private  String name;
    private  String position;
    private  BigDecimal price;
    private  int quantity;
    private  String sound;

    public Snack(String position, String name, BigDecimal price, int quantity) {
        this.position = position;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public  BigDecimal getPrice() {
        return price;
    }

    public  String getName() {
        return name;
    }

    public  String getPosition() {
        return position;
    }

    public int getQuantity() { return quantity;}

    public void setQuantity(int quantity) {
        this.quantity =  quantity;
    }

    public abstract String getSound();


}

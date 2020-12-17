package com.techelevator;

import java.math.BigDecimal;

public class Drink  extends Snack {

    public Drink(String position, String name, BigDecimal price, int quantity) {
        super(position, name, price, quantity);
    }

    @Override
    public  String getSound() {
        return "Glug Glug, Yum!";
    }
}

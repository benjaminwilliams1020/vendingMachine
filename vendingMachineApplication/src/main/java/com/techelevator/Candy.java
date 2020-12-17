package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Snack {

    public Candy(String position, String name, BigDecimal price, int quantity) {
        super(position, name, price, quantity);
    }

    @Override
    public  String getSound() {
        return "Munch Munch, Yum!";
    }
}

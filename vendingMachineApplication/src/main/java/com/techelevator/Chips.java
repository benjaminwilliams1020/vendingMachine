package com.techelevator;

import java.math.BigDecimal;

public class Chips extends Snack {

    public Chips(String position, String name, BigDecimal price, int quantity) {
        super(position, name, price, quantity);
    }

    @Override
    public  String getSound() {
       return "Crunch Crunch, Yum!";
    }
}

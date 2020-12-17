package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Snack {

    public Gum(String position, String name, BigDecimal price, int quantity) {
        super(position, name, price, quantity);
    }

    @Override
    public  String getSound() {
        return "Chew Chew, Yum!";
    }
}

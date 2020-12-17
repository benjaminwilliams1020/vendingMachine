package com.techelevator;

import java.math.BigDecimal;

public class Balance {

    private BigDecimal balance = new BigDecimal("0.00");
    private BigDecimal totalSales = new BigDecimal("0.00");



    public BigDecimal getBalance() {
        return balance;
    }

    public  void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void depositMoney(BigDecimal amount) {
             balance = balance.add(amount);
    }

    public void lessMoney(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

}

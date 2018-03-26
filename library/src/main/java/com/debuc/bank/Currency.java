package com.debuc.bank;

public class Currency {
    private double amount;

    private Currency(double amount) {
        this.amount = amount;
    }

    private static void checkNegativeValue (double amount) throws NegativeValueException {
        if(amount < 0) {
            throw new NegativeValueException();
        }
    }

    public static Currency create(double amount) throws NegativeValueException {
        checkNegativeValue(amount);
        return new Currency(amount);
    }

    public double add(double amount) throws NegativeValueException {
        checkNegativeValue(amount);
        this.amount+=amount;
        return amount;
    }

    public double deduct(double amount) throws NegativeValueException {
        checkNegativeValue(amount);
        this.amount-=amount;
        return amount;
    }

    public double getAmount() {
        return amount;
    }
}

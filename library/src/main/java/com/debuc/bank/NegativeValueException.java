package com.debuc.bank;

public class NegativeValueException extends Throwable {
    public NegativeValueException() {
        super("Amount can not be negative !");
    }
}

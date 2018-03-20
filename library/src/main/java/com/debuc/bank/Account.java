package com.debuc.bank;

public class Account {
    private String owner;
    private final String accountNo;
    private double balance;
    private Object accountOwner;

    public Account(String owner, String accountNo, double balance) throws MinimumBalanceException {
        checkMinimumBalance(balance);
        this.owner = owner;
        this.accountNo = accountNo;
        this.balance = balance;
    }

    private void checkMinimumBalance(double balance) throws MinimumBalanceException {
        if (balance<1000){
            throw new MinimumBalanceException();
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getAccountOwner() {
        return owner;
    }

    public double credit(double amount) {
        balance += amount;
        return balance;
    }

    public double debit(double amount) throws MinimumBalanceException {
        checkMinimumBalance(balance-amount);
        balance -= amount;
        return balance;
    }
}

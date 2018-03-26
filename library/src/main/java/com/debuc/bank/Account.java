package com.debuc.bank;

public class Account {
    private String owner;
    private final String accountNo;
    private Currency balance;
    private Object accountOwner;

    private Account(String owner, String accountNo, Currency balance) throws MinimumBalanceException {
        this.owner = owner;
        this.accountNo = accountNo;
        this.balance = balance;
    }

    private static void checkMinimumBalance(double balance) throws MinimumBalanceException {
        if (balance<1000){
            throw new MinimumBalanceException();
        }
    }

    public static Account create(String owner, String accountNo, double balance) throws MinimumBalanceException, NegativeValueException {
        checkMinimumBalance(balance);
        return new Account(owner, accountNo, Currency.create(balance));
    }

    public double getBalance() {
        return balance.getAmount();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getAccountOwner() {
        return owner;
    }

    public double credit(double amount) throws NegativeValueException {
        balance.add(amount);
        return balance.getAmount();
    }

    public double debit(double amount) throws MinimumBalanceException, NegativeValueException {
        checkMinimumBalance(balance.getAmount() - amount);
        balance.deduct(amount);
        return balance.getAmount();
    }
}

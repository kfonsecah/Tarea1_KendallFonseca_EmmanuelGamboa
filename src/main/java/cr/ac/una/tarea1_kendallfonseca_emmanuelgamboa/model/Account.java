package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import java.util.Objects;

import javafx.beans.property.*;


public class Account {

    private StringProperty accountNumber;
    private StringProperty accountType;
    private DoubleProperty balance;
    private StringProperty currency;
    private StringProperty accountHolder;
    private StringProperty associateIdentifier;


    public Account(String accountNumber, String accountType, double balance, String currency, String accountHolder, String associateIdentifier) {
        this.accountNumber = new SimpleStringProperty(accountNumber);
        this.accountType = new SimpleStringProperty(accountType);
        this.balance = new SimpleDoubleProperty(balance);
        this.currency = new SimpleStringProperty(currency);
        this.accountHolder = new SimpleStringProperty(accountHolder);
        this.associateIdentifier = new SimpleStringProperty(associateIdentifier);
    }

    public Account(String accountNumber, String accountType, double balance, String currency, String accountHolder) {
        this.accountNumber = new SimpleStringProperty(accountNumber);
        this.accountType = new SimpleStringProperty(accountType);
        this.balance = new SimpleDoubleProperty(balance);
        this.currency = new SimpleStringProperty(currency);
        this.accountHolder = new SimpleStringProperty(accountHolder);
    }

    public String getAccountNumber() {
        return accountNumber.get();
    }

    public StringProperty accountNumberProperty() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber.set(accountNumber);
    }

    public String getAccountType() {
        return accountType.get();
    }

    public StringProperty accountTypeProperty() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType.set(accountType);
    }

    public double getBalance() {
        return balance.get();
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance.set(balance);
    }

    public String getCurrency() {
        return currency.get();
    }

    public StringProperty currencyProperty() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency.set(currency);
    }

    public String getAccountHolder() {
        return accountHolder.get();
    }

    public StringProperty accountHolderProperty() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder.set(accountHolder);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    public String getAssociateIdentifier() {
        return associateIdentifier.get();
    }

    public StringProperty associateIdentifierProperty() {
        return associateIdentifier;
    }

    public void setAssociateIdentifier(String associateIdentifier) {
        this.associateIdentifier.set(associateIdentifier);
    }



    public void deposit(double amount) {
        if (amount > 0) {
            balance.set(balance.get() + amount);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance.get()) {
            balance.set(balance.get() - amount);
        }
    }

    public void transfer(Account recipient, double amount) {
        if (amount > 0 && amount <= balance.get()) {
            withdraw(amount);
            recipient.deposit(amount);
        }
    }
}
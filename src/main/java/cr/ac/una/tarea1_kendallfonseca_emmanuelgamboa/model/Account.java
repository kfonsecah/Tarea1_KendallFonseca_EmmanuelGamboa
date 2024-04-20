package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import java.util.Objects;

import javafx.beans.property.*;


public class Account {


    private DoubleProperty balance;
    private StringProperty currency;
    private StringProperty accountHolder;
    private String Folio;


    public Account() {
    }


    public Account(double balance, String currency, String accountHolder, String folio) {

        this.balance = new SimpleDoubleProperty(balance);
        this.currency = new SimpleStringProperty(currency);
        this.accountHolder = new SimpleStringProperty(accountHolder);
        this.Folio = String.valueOf(new SimpleStringProperty(folio));
        //this.associateIdentifier = new SimpleStringProperty(associateIdentifier);
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

    public String getFolio() {
        return Folio;
    }

    public void setFolio(String folio) {
        Folio = folio;
    }
}
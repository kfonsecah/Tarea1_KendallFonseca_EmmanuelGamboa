package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import java.util.Objects;

import javafx.beans.property.*;


public class Account {


    private Double balance;
    private String currency;
    private String accountHolder;
    private String Folio;


    public Account() {
    }


    public Account(double balance, String currency, String accountHolder, String folio) {

        this.balance = balance;
        this.currency= currency;
        this.accountHolder = accountHolder;
        this.Folio = folio;
    }




    public double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountHolder() {
        return accountHolder;
    }
    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }









    public String getFolio() {
        return Folio;
    }

    public void setFolio(String folio) {
        Folio = folio;
    }
}
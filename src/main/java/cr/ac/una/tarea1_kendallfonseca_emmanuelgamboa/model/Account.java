package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import java.util.Objects;


public class Account {


    private int balance;
    private String accountType;
    private String currency;
    private String accountHolder;
    private String Folio;


    public Account() {
    }


    public Account(int balance, String currency, String accountType, String accountHolder, String folio) {

        this.balance = balance;
        this.currency= "Colones";
        this.accountType = accountType;
        this.accountHolder = accountHolder;
        this.Folio = folio;
    }




    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
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
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Account account = (Account) obj;
        return Objects.equals(accountType, account.getAccountType()) &&
                Objects.equals(Folio, account.getFolio()) &&
                Objects.equals(accountHolder, account.getAccountHolder());
    }


}
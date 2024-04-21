package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.*;
import javafx.scene.control.ListCell;


public class Account {


    private Double balance;
    private String accountType;
    private String currency;
    private String accountHolder;
    private String Folio;


    public Account() {
    }


    public Account(double balance, String currency, String accountType, String accountHolder, String folio) {

        this.balance = balance;
        this.currency= "Colones";
        this.accountType = accountType;
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
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    private class AccountListCell extends ListCell<Account> {
        @Override
        protected void updateItem(Account account, boolean empty) {
            super.updateItem(account, empty);
            if (empty || account == null) {
                setText(null);
            } else {
                setText(account.getAccountHolder() + " - " + account.getAccountType() + " - " + account.getCurrency());
            }
        }
    }

}
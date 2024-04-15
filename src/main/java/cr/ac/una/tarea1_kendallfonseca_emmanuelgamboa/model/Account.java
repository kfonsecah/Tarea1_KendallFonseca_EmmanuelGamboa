package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.*;


public class Account {


    private StringProperty accountNumber;
    private StringProperty accountType;
    private DoubleProperty balance;
    private StringProperty currency;
    private StringProperty accountHolder;
    private BooleanProperty active;

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

    public boolean isActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    public static Account fromString(String accountString) {
        try {
            System.out.println("Account string: " + accountString); // Mensaje de depuración
            String[] accountData;
            // Try splitting by comma first
            // If comma fails, try splitting by slash
            accountData = accountString.split("/");
            if (accountData.length != 5) {
                throw new IllegalArgumentException("Invalid account string format. Expected 6 fields (comma or slash separated).");
            }
            // Resto del código
        } catch (IllegalArgumentException e) {
            System.err.println("Error parsing account string: " + e.getMessage());
        }
        return null;
    }



}


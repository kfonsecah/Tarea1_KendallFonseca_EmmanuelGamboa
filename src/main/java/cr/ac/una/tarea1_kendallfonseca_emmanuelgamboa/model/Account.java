package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

            // Crear una nueva instancia de Account con los datos obtenidos
            String accountNumber = accountData[0];
            String accountType = accountData[1];
            double balance = Double.parseDouble(accountData[2].replaceAll("[^\\d.]", ""));
            String currency = accountData[3];
            String accountHolder = accountData[4];

            return new Account(accountNumber, accountType, balance, currency, accountHolder);
        } catch (IllegalArgumentException e) {
            System.err.println("Error parsing account string: " + e.getMessage());
            throw e; // Relanzar la excepción para que la clase que llama pueda manejarla adecuadamente
        }

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }
}
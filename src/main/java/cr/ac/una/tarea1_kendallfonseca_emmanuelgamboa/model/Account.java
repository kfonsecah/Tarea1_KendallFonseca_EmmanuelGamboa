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

    public Account(String accountNumber, String accountType, double balance, String currency, String accountHolder, boolean active) {
        this.accountNumber = new SimpleStringProperty(accountNumber);
        this.accountType = new SimpleStringProperty(accountType);
        this.balance = new SimpleDoubleProperty(balance);
        this.currency = new SimpleStringProperty(currency);
        this.accountHolder = new SimpleStringProperty(accountHolder);
        this.active = new SimpleBooleanProperty(active);
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
            String[] accountData;
            // Try splitting by comma first
            accountData = accountString.split(",");
            if (accountData.length != 6) {
                // If comma fails, try splitting by slash
                accountData = accountString.split("/");
                if (accountData.length != 6) {
                    throw new IllegalArgumentException("Invalid account string format. Expected 6 fields (comma or slash separated).");
                }
            }

            // Parse data from each field
            String name = accountData[0];
            String identification = accountData[1];
            String ageStr = accountData[2]; // Store age as a String initially
            String address = accountData[3];
            String phone = accountData[4];
            boolean isActive = accountData[5].equalsIgnoreCase("inactive");

            // Attempt to parse age as an integer (if possible)
            int age = 0;
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException e) {
                // If parsing fails, store age as a String
                System.err.println("Warning: Unable to parse age as integer. Storing as String: " + ageStr);
            }

            // Create and return a new Account object
            return new Account(name, identification, age, address, phone, isActive);
        } catch (IllegalArgumentException e) {
            // Handle invalid format gracefully...
        }

        return null;
    }
}


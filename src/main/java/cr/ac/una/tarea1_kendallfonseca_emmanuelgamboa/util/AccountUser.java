package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;

import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.ArrayList;
import java.util.List;

public class AccountUser {

    private String userName;
    private ObservableList<Account> accountsObservableList;

    public AccountUser() {
        AppContext appContext = AppContext.getInstance();
        accountsObservableList = AppContext.getAccounts();
    }

    public AccountUser(String userName) {
        this.userName = userName;
    }

    public ObservableList<Account> getAccountsObservableList() {
        return accountsObservableList;
    }

    public void setAccountsObservableList(ObservableList<Account> accountsObservableList) {
        this.accountsObservableList = accountsObservableList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addAccount(Account account) {
        accountsObservableList.add(account);
    }

    public List<Account> getAccountsByFolio(String folio) {
        List<Account> accountsByFolio = new ArrayList<>();
        for (Account account : accountsObservableList) {
            if (account.getFolio().equals(folio)) {
                accountsByFolio.add(account);
            }
        }
        return accountsByFolio;
    }
}


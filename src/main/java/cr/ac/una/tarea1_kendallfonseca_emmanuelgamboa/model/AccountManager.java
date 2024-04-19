package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private static AccountManager instance;
    private Map<String, ObservableList<Account>> accountMap;

    private AccountManager() {
        accountMap = new HashMap<>();
    }

    public static AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;
    }

    public void addAssociatedAccounts(String folio, ObservableList<Account> accounts) {
        accountMap.put(folio, accounts);
    }

    public ObservableList<Account> getAssociatedAccounts(String folio) {
        return accountMap.get(folio);
    }

    public void clear() {
        accountMap.clear();
    }

    public void writeAccountsToFile() {

    }


}


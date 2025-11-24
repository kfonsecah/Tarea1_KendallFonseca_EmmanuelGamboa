package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Deposits;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class AccountUser {

    private String userName;
    private ObservableList<Account> accountsObservableList;
    private Map<String, ObservableList<Account>> cuentasPorFolioYTipo;

    public AccountUser() {
        AppContext appContext = AppContext.getInstance();
        accountsObservableList = AppContext.getAccounts();
        cuentasPorFolioYTipo = new HashMap<>();
        actualizarCuentasPorFolioYTipo();
    }

    public ObservableList<Deposits> getAccountMovements(String folio, String type) throws IOException {
        ObservableList<Deposits> movements = FXCollections.observableArrayList();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("deposits.json");
        if (file.exists()) {
            List<Deposits> deposits = objectMapper.readValue(file, new TypeReference<List<Deposits>>() {
            });
            for (Deposits deposit : deposits) {
                if (deposit.getFolio().equals(folio) && deposit.getTipoCuenta().equals(type)) {
                    movements.add(deposit);
                }
            }
        }
        return movements;
    }

    public AccountUser(String userName) {
        this.userName = userName;
    }

    public ObservableList<Account> getAccountsObservableList() {
        return accountsObservableList;
    }

    public void setAccountsObservableList(ObservableList<Account> accountsObservableList) {
        this.accountsObservableList = accountsObservableList;
        actualizarCuentasPorFolioYTipo();
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addAccount(Account account) {
        accountsObservableList.add(account);
        actualizarCuentasPorFolioYTipo();
    }

    public ObservableList<Account> getAccountsByFolio(String folio) {
        ObservableList<Account> accountsByFolio = FXCollections.observableArrayList();
        for (Account account : accountsObservableList) {
            if (account.getFolio().equals(folio)) {
                accountsByFolio.add(account);
            }
        }
        return accountsByFolio;
    }

    public ObservableList<Account> getAccountsByFolioAndType(String folio, String tipoCuenta) {
        String clave = folio + "_" + tipoCuenta;
        return cuentasPorFolioYTipo.getOrDefault(clave, FXCollections.observableArrayList());
    }

    public void realizarDeposito(String folio, String tipoCuenta, int monto) throws IOException {
        // Iterar sobre todas las cuentas disponibles
        for (Account cuenta : accountsObservableList) {
            if (cuenta.getFolio().equals(folio) && cuenta.getAccountType().equals(tipoCuenta)) {
                int saldoActual = (int) cuenta.getBalance();
                int nuevoSaldo = saldoActual + monto;
                cuenta.setBalance(nuevoSaldo);
            }
        }
        saveAccountsToJsonFile();
        AppContext.getInstance().setAccountsObservableList(accountsObservableList);
    }


    public void setDepositInProcessFalseForDeposit(Deposits deposit) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("deposits.json");
        if (file.exists()) {
            List<Deposits> deposits = objectMapper.readValue(file, new TypeReference<List<Deposits>>() {
            });
            for (Deposits d : deposits) {
                if (d.equals(deposit)) {
                    d.setInProcess(false);
                    break;
                }
            }
            objectMapper.writeValue(file, deposits);
        }
    }

    private void saveAccountsToJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("accounts.json");
        try {
            List<Account> existingAccounts = objectMapper.readValue(jsonFile, new TypeReference<List<Account>>() {
            });
            for (Account updatedAccount : accountsObservableList) {
                for (Account existingAccount : existingAccounts) {
                    if (existingAccount.getFolio().equals(updatedAccount.getFolio()) && existingAccount.getAccountType().equals(updatedAccount.getAccountType())) {
                        existingAccount.setBalance(updatedAccount.getBalance());
                        break;
                    }
                }
            }
            //REESCRIBIRLAS DE NUEVO
            objectMapper.writeValue(jsonFile, existingAccounts);
        } catch (IOException e) {
            System.out.println("Error saving accounts to JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void acceptDeposit(Deposits deposit, ObservableList<Account> accounts) throws IOException {
        for (Account account : accounts) {
            if (account.getAccountType().equals(deposit.getTipoCuenta()) && account.getFolio().equals(deposit.getFolio())) {
                account.setBalance((int) (account.getBalance() + deposit.getMoneda()));
                System.out.println("Updated account balance: " + account.getBalance());
                break;
            }
        }
        removeDepositFromJsonFile(deposit);
        AppContext.addDepositToJsonFile(deposit);
    }


    public static void removeDepositFromJsonFile(Deposits depositToRemove) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("deposits.json");
        if (file.exists()) {
            List<Deposits> deposits = objectMapper.readValue(file, new TypeReference<List<Deposits>>() {
            });
            System.out.println("Deposits before removal: " + deposits);

            //iterar sobre todos los depositos
            Iterator<Deposits> iterator = deposits.iterator();
            while (iterator.hasNext()) {
                Deposits deposit = iterator.next();
                if (deposit.getTipoCuenta().equals(depositToRemove.getTipoCuenta()) && deposit.getFolio().equals(depositToRemove.getFolio()) && deposit.getMoneda() == depositToRemove.getMoneda()) {
                    iterator.remove();
                    System.out.println("Deposit removed: " + deposit);
                    break;
                }
            }
            objectMapper.writeValue(file, deposits);
            System.out.println("Deposits after removal: " + deposits);
        }
    }

    private void actualizarCuentasPorFolioYTipo() {
        for (Account account : accountsObservableList) {
            String clave = account.getFolio() + "_" + account.getAccountType();
            if (cuentasPorFolioYTipo.containsKey(clave)) {
                cuentasPorFolioYTipo.get(clave).add(account);
            } else {
                ObservableList<Account> newList = FXCollections.observableArrayList();
                newList.add(account);
                cuentasPorFolioYTipo.put(clave, newList);
            }
        }
    }

    public void removeAccountFromJsonFile(Account accountToRemove) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("accounts.json");
        if (file.exists()) {
            List<Account> accounts = objectMapper.readValue(file, new TypeReference<List<Account>>() {
            });
            Iterator<Account> iterator = accounts.iterator();
            while (iterator.hasNext()) {
                Account account = iterator.next();
                if (account.getFolio().equals(accountToRemove.getFolio()) && account.getAccountType().equals(accountToRemove.getAccountType())) {
                    iterator.remove();
                    break;
                }
            }
            objectMapper.writeValue(file, accounts);
        }
    }

    public void setDepositInProcessFalse(Deposits deposit) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("deposits.json");
        if (file.exists()) {
            List<Deposits> deposits = objectMapper.readValue(file, new TypeReference<List<Deposits>>() {
            });
            for (Deposits d : deposits) {
                if (d.equals(deposit)) {
                    d.setInProcess(false);
                    break;
                }
            }
            objectMapper.writeValue(file, deposits);
        }
    }

    public void realizarRetiro(String folio, String tipoCuenta, int monto) throws IOException {

        for (Account cuenta : accountsObservableList) {
            if (cuenta.getFolio().equals(folio) && cuenta.getAccountType().equals(tipoCuenta)) {
                // Realizar el retiro en la cuenta encontrada
                int saldoActual = (int) cuenta.getBalance();
                int nuevoSaldo = saldoActual - monto;
                if (nuevoSaldo >= 0) {
                    cuenta.setBalance(nuevoSaldo);
                    System.out.println("Retiro realizado correctamente. Nuevo saldo: " + nuevoSaldo);
                } else {
                    System.out.println("Error: Fondos insuficientes para realizar el retiro.");
                }
                break;
            }
        }
        // Actualizar la lista observable despues de realizar el retiro
        saveAccountsToJsonFile();
        AppContext.getInstance().setAccountsObservableList(accountsObservableList);


    }


}




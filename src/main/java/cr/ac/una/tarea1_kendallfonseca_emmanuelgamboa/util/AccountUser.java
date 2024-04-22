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

    public void realizarDeposito(String folio, String tipoCuenta, int monto) {
        String clave = folio + "_" + tipoCuenta;
        ObservableList<Account> cuentas = cuentasPorFolioYTipo.get(clave);
        if (cuentas == null || cuentas.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron cuentas asociadas con el folio y tipo de cuenta proporcionados.");
        }
        for (Account cuenta : cuentas) {
            int saldoActual = (int) cuenta.getBalance();
            int nuevoSaldo = saldoActual + monto;
            cuenta.setBalance(nuevoSaldo);
        }
        // Actualizar la lista observable después de realizar el depósito
        accountsObservableList.setAll(cuentas);
        // Escribir la información de la cuenta actualizada de vuelta al archivo JSON
        saveAccountsToJsonFile();
        // También actualizamos el contexto de la aplicación
        AppContext.getInstance().setAccountsObservableList(accountsObservableList);
    }

    private void saveAccountsToJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("accounts.json");
        try {
            // Read existing accounts from JSON file
            List<Account> existingAccounts = objectMapper.readValue(jsonFile, new TypeReference<List<Account>>() {});

            // Update balances in existing accounts
            for (Account updatedAccount : accountsObservableList) {
                for (Account existingAccount : existingAccounts) {
                    if (existingAccount.getFolio().equals(updatedAccount.getFolio()) && existingAccount.getAccountType().equals(updatedAccount.getAccountType())) {
                        existingAccount.setBalance(updatedAccount.getBalance());
                        break;
                    }
                }
            }

            // Write the updated accounts back to the JSON file
            objectMapper.writeValue(jsonFile, existingAccounts);
        } catch (IOException e) {
            System.out.println("Error saving accounts to JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }






    public void acceptDeposit(Deposits deposit, ObservableList<Account> accounts) throws IOException {
        // Update the account balance
        for (Account account : accounts) {
            if (account.getAccountType().equals(deposit.getTipoCuenta()) && account.getFolio().equals(deposit.getFolio())) {
                account.setBalance((int) (account.getBalance() + deposit.getMoneda()));
                System.out.println("Updated account balance: " + account.getBalance());
                break;
            }
        }

        // Save the updated balance back to the JSON file
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

            // Iterate over all deposits in the JSON file
            Iterator<Deposits> iterator = deposits.iterator();
            while (iterator.hasNext()) {
                Deposits deposit = iterator.next();
                // Check if the deposit matches the deposit to remove
                if (deposit.getTipoCuenta().equals(depositToRemove.getTipoCuenta()) && deposit.getFolio().equals(depositToRemove.getFolio()) && deposit.getMoneda() == depositToRemove.getMoneda()) {
                    iterator.remove(); // Remove the deposit from the list
                    System.out.println("Deposit removed: " + deposit);
                    break; // Exit the loop after removing the deposit
                }
            }

            objectMapper.writeValue(file, deposits);
            System.out.println("Deposits after removal: " + deposits);
        }
    }


    private void actualizarCuentasPorFolioYTipo() {
        cuentasPorFolioYTipo.clear();
        for (Account account : accountsObservableList) {
            String clave = account.getFolio() + "_" + account.getAccountType();
            cuentasPorFolioYTipo.putIfAbsent(clave, FXCollections.observableArrayList());
            cuentasPorFolioYTipo.get(clave).add(account);
        }
    }
}




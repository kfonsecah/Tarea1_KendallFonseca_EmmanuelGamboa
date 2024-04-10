package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountType {

    private List<AccountType> accounts = new ArrayList<>();
    private String name;


    public AccountType() {
    }

    public AccountType(String name) {
        this.name = name;
    }

    public List<AccountType> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountType> accounts) {
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAccount(AccountType account) {
        accounts.add(account);
    }

    public void deleteAccount(AccountType account) {
        accounts.remove(account);
    }

    public void addToFile(AccountType accountType) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Cuentas.txt", true));

        try {
            writer.write(getName());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Cuentas.txt"));

        try {
            writer.write("Cuenta Corriente");
            writer.newLine();
            writer.write("Ahorro a la vista");
            writer.newLine();
            writer.write("Cuenta Naranja");
            writer.newLine();
            writer.write("Cuenta Objetivo");
            writer.newLine();
            writer.write("Pequenhos ahorros");
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AccountType {

    public ArrayList<String> cuentas = new ArrayList<String>();

    public String name;



    public AccountType() {
    }

    public AccountType(String name){
        this.name = name;

    }


    public ArrayList<String> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<String> cuentas) {
        this.cuentas = cuentas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addAccount(){
        AccountType accounts = new AccountType(name);
        accounts.cuentas.add(accounts.getName());


    }

    public void deleteAccount(){
        AccountType accounts = new AccountType(name);
        accounts.cuentas.remove(accounts.getName());

    }

    public void addToFile(AccountType account) throws IOException {

        account.addAccount();
        BufferedWriter writer = new BufferedWriter(new FileWriter("Cuentas.txt",true));

        try {

            writer.write(account.getCuentas().toString());
            writer.newLine();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile(AccountType account) throws IOException {
        account.addToFile(account);
    }

}

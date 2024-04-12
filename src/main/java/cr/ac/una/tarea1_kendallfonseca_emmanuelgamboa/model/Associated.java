package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import javafx.scene.Node;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Associated {
    public String AssoName;
    public String AssoLastName;
    public int AssoAge;
    public String AssoPhoto;
    public String AssoFolio;
    public String AssoCuenta;
    private String iban;
    public ArrayList<String> Associated= new ArrayList<String>();
    private List<Account> accounts = new ArrayList<>();

    public Associated() {
    }
    public Associated(String name, String lastName, int age, String folio, String photo, String iban) {
        this.AssoName = name;
        this.AssoLastName = lastName;
        this.AssoAge = age;
        this.AssoFolio = folio;
        this.AssoPhoto = photo;
        this.iban = iban;

    }


    public Account createAccount() {
        String accountNumber = createAccountNumber();
        Account account = new Account(accountNumber, "Savings", 0, "CRC", this.AssoName + " " + this.AssoLastName, false);
        this.accounts.add(account);
        return account;
    }

    public String createAccountNumber() {
        Random random = new Random();
        long randomNumber = Math.abs(random.nextLong()); // Generate a random long integer
        String cuenta = String.format("%019d", randomNumber); // Format the integer with leading zeros
        return cuenta;
    }



    public ArrayList<String> getAssociated() {
        return Associated;
    }
    public void setAssociated(ArrayList<String> associated) {
        Associated = associated;
    }
    public String getAssoName() {
        return AssoName;
    }
    public void setAssoName(String name) {
        this.AssoName = name;
    }
    public String getAssoLastName() {
        return AssoLastName;
    }
    public void setAssoLastName(String lastName) {
        this.AssoLastName = lastName;
    }
    public int getAssoAge() {
        return AssoAge;
    }
    public void setAssoAge(int age) {
        this.AssoAge = age;
    }
    public String getAssoPhoto() {
        return getAssoFolio()+".png";
    }
    public void setAssoPhoto(String photo) {
        this.AssoPhoto = getAssoFolio()+".png";
    }
    public String getAssoFolio() {
        return AssoFolio;
    }
    public void setAssoFolio(String folio) {
        this.AssoFolio = folio;
    }
    public String getAssoCuentas() {
        return AssoCuenta;
    }
    public void setAssoCuentas(String cuenta) {
        this.AssoCuenta = cuenta;
    }
    public String getIban() {
        return iban;
    }
    public void setIban(String iban) {
        this.iban = iban;
    }

    public String createFolio() {
        Random random = new Random();
        char firstLetter = AssoName.charAt(0);
        char firstLetterLT = AssoLastName.charAt(0);
        int randomNumber = random.nextInt(1000);
        String folio = String.valueOf(firstLetter) + String.valueOf(firstLetterLT) + String.format("%04d", randomNumber);
        this.AssoFolio = folio;
        return folio;
    }
    public void addAssociated() {
        this.Associated.add(this.getAssoName());
        this.Associated.add(this.getAssoLastName());
        this.Associated.add(String.valueOf(this.getAssoAge()));
        this.Associated.add(this.getAssoFolio());
        this.Associated.add(this.getAssoPhoto());
        this.Associated.add(this.getIban());
    }

    public void addToFile(Associated associated) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("Asociados.txt",true));
        try {
            writer.write(associated.getAssociated().toString());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile(Associated associated) throws IOException {
        associated.addToFile(associated);
    }



    public void printAssociated(){
        for (String aso: Associated) {
            System.out.println(aso);
        }
    }
    public String createIban() {
        this.iban = "CR" + createAccountNumber();
        return iban;
    }
}


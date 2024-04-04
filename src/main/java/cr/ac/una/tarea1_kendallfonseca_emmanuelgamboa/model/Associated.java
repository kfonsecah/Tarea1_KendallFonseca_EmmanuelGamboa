package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import javafx.scene.Node;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Associated {
    public String AssoName;
    public String AssoLastName;
    public int AssoAge;
    public String AssoPhoto;
    public String AssoFolio;
    public String AssoCuenta;
    public ArrayList<String> Associated= new ArrayList<String>();

    public Associated() {

    }
    public Associated(String name, String lastName, int age, String folio, String photo) {
        this.AssoName = name;
        this.AssoLastName = lastName;
        this.AssoAge = age;
        this.AssoFolio = folio;
        this.AssoPhoto = photo;

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



}


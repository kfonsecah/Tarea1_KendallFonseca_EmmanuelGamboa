package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

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
    public Associated(String name, String lastName, int age, String photo) {
        this.AssoName = name;
        this.AssoLastName = lastName;
        this.AssoAge = age;
        this.AssoPhoto = photo;
        this.AssoFolio = createFolio();
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

    private String createFolio() {
        Random random = new Random();
        char firstLetter = AssoName.charAt(0);
        char firstLetterLT = AssoLastName.charAt(0);
        int randomNumber = random.nextInt(1000);
        return String.valueOf(firstLetter) + String.valueOf(firstLetterLT) + String.format("%04d", randomNumber);
    }
    public void addAssociated() {
        Associated associated = new Associated(AssoName, AssoLastName, AssoAge, AssoPhoto);
        associated.Associated.add(associated.getAssoName());
        associated.Associated.add(String.valueOf(associated.getAssoAge()));
        associated.Associated.add(associated.createFolio());
        associated.Associated.add(associated.getAssoPhoto());


    }

    public void printAssociated(){
        for (String aso: Associated) {
            System.out.println(aso);
        }
    }

}

package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import java.io.*;


public class AccountType {
    private String name;

    public AccountType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //method to show the string name
    public String toString() {
        return name;
    }
}


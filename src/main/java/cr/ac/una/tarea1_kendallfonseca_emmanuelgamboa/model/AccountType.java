package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    public void setNameFromFirstInFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String firstAccountTypeName = reader.readLine();
            if (firstAccountTypeName != null) {
                name = firstAccountTypeName.trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //method to show the string name
    public String toString() {
        return name;
    }
}
// Path: src/main/java/cr/ac/una/tarea1_kendallfonseca_emmanuelgamboa/model/Associated.java

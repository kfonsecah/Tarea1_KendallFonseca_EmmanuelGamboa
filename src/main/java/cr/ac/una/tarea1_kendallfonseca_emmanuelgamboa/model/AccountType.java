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

    @Override
    public String toString() {
        return name;
    }

    public void applyNameFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    this.name = line.trim();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

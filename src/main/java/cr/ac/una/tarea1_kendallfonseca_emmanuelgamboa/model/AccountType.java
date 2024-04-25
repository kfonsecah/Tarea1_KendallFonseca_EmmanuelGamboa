package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import java.io.*;
import java.util.Objects;

public class AccountType {
    private String name;


    public AccountType() {
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AccountType other = (AccountType) obj;
        return Objects.equals(name, other.name);
    }


}


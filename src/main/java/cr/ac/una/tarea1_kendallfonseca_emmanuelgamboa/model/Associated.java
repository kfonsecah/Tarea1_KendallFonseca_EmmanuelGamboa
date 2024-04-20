package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedWriter;
import java.io.File;
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

    //list for the accounts
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
        Account account = new Account("Savings", 0, "CRC", this.AssoName + " " + this.AssoLastName);
        this.accounts.add(account);
        return account;
    }

    public String createAccountNumber() {
        Random random = new Random();
        long randomNumber = Math.abs(random.nextLong());
        String cuenta = String.format("%019d", randomNumber);
        return cuenta;
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

    public String getName() {
        return AssoName;
    }

    public void setName(String name) {
        AssoName = name;
    }
    public String getLastName() {
        return AssoLastName;
    }
    public void setLastName(String lastName) {
        AssoLastName = lastName;
    }
    public int getAge() {
        return AssoAge;
    }
    public void setAge(int age) {
        AssoAge = age;
    }
    public String getPhoto() {
        return getAssoFolio()+".png";
    }
    public void setPhoto(String photo) {
        this.AssoPhoto = getAssoFolio()+".png";
    }
    public String getFolio() {
        return AssoFolio;
    }
    public void setFolio(String folio) {
        this.AssoFolio = folio;
    }
    public String getCuentas() {
        return AssoCuenta;
    }
    public void setCuentas(String cuenta) {
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


    public static class AssociatedData {
        public String name;
        public String lastName;
        public int age;
        public String folio;
        public String photo;
        public String iban;
        public List<Account> accounts;

        public AssociatedData() {
        }

        public AssociatedData(String name, String lastName, int age, String folio, String photo, String iban, List<Account> accounts) {
            this.name = name;
            this.lastName = lastName;
            this.age = age;
            this.folio = folio;
            this.photo = photo;
            this.iban = iban;
            this.accounts = accounts;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public String getFolio() {
            return folio;
        }
        public void setFolio(String folio) {
            this.folio = folio;
        }
        public String getPhoto() {
            return photo;
        }
        public void setPhoto(String photo) {
            this.photo = photo;
        }
        public List<Account> getAccounts() {
            return accounts;
        }
        public void setAccounts(List<Account> accounts) {
            this.accounts = accounts;
        }
    }


    public static void addAssociatedToJsonFile(Associated.AssociatedData associatedData) throws IOException {
        File file = new File("associateds.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        if (!file.exists()) {
            file.createNewFile();
            objectMapper.writeValue(file, new ArrayList<Associated.AssociatedData>());
        }

        List<Associated.AssociatedData> associatedDataList = objectMapper.readValue(file, ArrayList.class);
        associatedDataList.add(associatedData);
        objectMapper.writeValue(file, associatedDataList);
    }



    public String createIban() {
        this.iban = "CR" + createAccountNumber();
        return iban;
    }

    //method to get the accounts
    public List<Account> getAccounts() {
        return accounts;
    }
    //method to set the accounts
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    //method to add an account
    public void addAccount(Account account) {
        accounts.add(account);
    }
    //method to remove an account
    public void removeAccount(Account account) {
        accounts.remove(account);
    }
    //check that the accounts are empty
    public boolean accountsIsEmpty() {
        return accounts.isEmpty();
    }
    //method to get the last account
    public Account getLastAccount() {
        return accounts.get(accounts.size() - 1);
    }
}


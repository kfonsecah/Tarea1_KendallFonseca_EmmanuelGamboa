package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
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
    private String iban;

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


    public String createFolio() {
        Random random = new Random();
        char firstLetter = Character.toUpperCase(AssoName.charAt(0)); // Convertir primera letra del nombre a mayuscula
        char firstLetterLT = Character.toUpperCase(AssoLastName.charAt(0)); // Convertir primera letra del apellido a mayuscula
        int randomNumber = random.nextInt(1000);
        String folio = String.valueOf(firstLetter) + String.valueOf(firstLetterLT) + String.format("%04d", randomNumber);
        this.AssoFolio = folio.toUpperCase(); // Convertir el folio completo a mayusculas
        return folio.toUpperCase();
    }

    public static class AssociatedData {
        public String name;
        public String lastName;
        public int age;
        public String folio;
        public String photo;
        public String iban;


        public AssociatedData(String name, String lastName, int age, String folio, String photo, String iban) {
            this.name = name;
            this.lastName = lastName;
            this.age = age;
            this.folio = folio;
            this.photo = photo;
            this.iban = iban;
        }
        public AssociatedData() {
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
        public String getIban() {
            return iban;
        }
        public void setIban(String iban) {
            this.iban = iban;
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

}


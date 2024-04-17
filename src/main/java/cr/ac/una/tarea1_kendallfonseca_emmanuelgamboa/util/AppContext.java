package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.AccountManager;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Cooperative;
import javafx.collections.FXCollections;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javafx.collections.ObservableList;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
import javafx.collections.ObservableMap;
import javafx.scene.image.Image;

import java.util.logging.Level;


public class AppContext {

    private static AppContext INSTANCE = null;

    private static final String USERS_FILE_PATH = "Asociados.txt";
    private static final String INACTIVE_ACCOUNTS_FILE_PATH = "inactiveAccounts.txt";
    private static final String ACTIVE_ACCOUNTS_FILE_PATH = "activeAccounts.txt";
    private static final ObservableList<Account> inactiveAccounts = FXCollections.observableArrayList();
    private static final ObservableList<Account> activeAccounts = FXCollections.observableArrayList();
    private static HashMap<String, Object> context = new HashMap<>();
    private static final ObservableList<Associated> asociados = FXCollections.observableArrayList();
    private ObservableMap<String, Cooperative> cooperatives = FXCollections.observableHashMap();



    private AppContext() {

        readUsers();
        readInactiveAccounts();
        readActiveAccounts();
        readAssociatedsFromJsonFile();

    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (AppContext.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppContext();
                }
            }
        }
    }

    public static AppContext getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }

    public static void saveAccounts() {
    }

    private void cargarPropiedades(){
        try {
            FileInputStream configFile;
            configFile = new FileInputStream("config/properties.ini");
            Properties appProperties = new Properties();
            appProperties.load(configFile);
            configFile.close();

        } catch (IOException io) {
            System.out.println("Archivo de configuración no encontrado.");
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public Object get(String parameter){
        return context.get(parameter);
    }

    public void set(String nombre, Object valor) {
        context.put(nombre, valor);
    }

    public void delete(String parameter) {
        context.put(parameter, null);
    }

    public static void readUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] userData = line.replaceAll("\\[|\\]", "").split(", ");
                    if (userData.length == 6) {
                        String name = userData[0];
                        String lastName = userData[1];
                        int age = Integer.parseInt(userData[2]);
                        String folio = userData[3];
                        String imageName = userData[4];
                        String iban = userData[5];

                        Associated associated = new Associated(name, lastName, age, folio, imageName, iban);
                        asociados.add(associated);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users from the file: " + e.getMessage());
        }

        for (Associated associated : asociados) {
            ObservableList<Account> associatedAccounts = getAccountsByFolio(associated.getAssoFolio());
            AccountManager.getInstance().addAssociatedAccounts(associated.getAssoFolio(), associatedAccounts);
        }
        AccountManager.getInstance().writeAccountsToFile();
        context.put("asociados", asociados);
    }

    public static void readAssociatedsFromJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Associated> associateds = objectMapper.readValue(new File("associateds.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Associated.class));
            ObservableList<Associated> asociados = FXCollections.observableArrayList(associateds);
            context.put("asociados", asociados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ObservableList<Account> getAccountsByFolio(String folio) {
        ObservableList<Account> associatedAccounts = FXCollections.observableArrayList();
        for (Account account : inactiveAccounts) {
            if (account.getAccountHolder().equals(folio)) {
                associatedAccounts.add(account);
            }
        }
        return associatedAccounts;
    }

    public static void readInactiveAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(INACTIVE_ACCOUNTS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    // Eliminar los corchetes "[" y "]" alrededor de la línea
                    line = line.substring(1, line.length() - 1);

                    // Dividir la línea en base al delimitador "/"
                    String[] accountData = line.split("/");
                    if (accountData.length == 5) { // Verificar si hay seis partes en la línea
                        String accountNumber = accountData[0];
                        String accountType = accountData[1];
                        double balance = Double.parseDouble(accountData[2].replace(",", ".")); // Reemplazar "," por "." para el formato correcto de double
                        String currency = accountData[3];
                        String accountHolder = accountData[4];
                        Account account = new Account(accountNumber, accountType, balance, currency, accountHolder);
                        inactiveAccounts.add(account);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading inactive accounts from the file: " + e.getMessage());
        }
        context.put("inactiveAccounts", inactiveAccounts);
    }


    private static void readActiveAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACTIVE_ACCOUNTS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    // Dividir la línea en base al delimitador "/"
                    String[] accountData = line.split("/");
                    if (accountData.length == 2) { // Verificar si hay dos partes en la línea
                        String accountInfo = accountData[0]; // Obtener la información de la cuenta
                        String activeStatus = accountData[1]; // Obtener el estado activo/inactivo

                        // Dividir la información de la cuenta en base al delimitador ","
                        String[] accountInfoParts = accountInfo.split(",");
                        if (accountInfoParts.length == 5) { // Verificar si hay seis partes en la información de la cuenta
                            String accountNumber = accountInfoParts[0];
                            String accountType = accountInfoParts[1];
                            double balance = Double.parseDouble(accountInfoParts[2]);
                            String currency = accountInfoParts[3];
                            String accountHolder = accountInfoParts[4];

                            Account account = new Account(accountNumber, accountType, balance, currency, accountHolder);
                            activeAccounts.add(account);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading active accounts from the file: " + e.getMessage());
        }
        context.put("activeAccounts", activeAccounts);
    }

    public void addCooperative(Cooperative cooperative) {
        cooperatives.put("cooperative", cooperative);
    }

    public Cooperative getCooperative(String name) {
        return cooperatives.get("cooperative");
    }
    public static ObservableList<Associated> getAsociados() {
        return asociados;
    }
//    public static ObservableList<Account> getInactiveAccounts() {
//        return inactiveAccounts;
//    }
//    public static ObservableList<Account> getActiveAccounts() {
//        return activeAccounts;
//    }


    public static ObservableList<Account> getActiveAccounts() {
        return FXCollections.observableArrayList(activeAccounts);
    }

    public static ObservableList<Account> getInactiveAccounts() {
        return FXCollections.observableArrayList(inactiveAccounts);
    }

    public void writeInactiveAccounts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INACTIVE_ACCOUNTS_FILE_PATH))) {
            for (Account account : inactiveAccounts) {
                writer.write(String.format("[%s/%s/%.2f/%s/%s]%n",
                        account.getAccountNumber(),
                        account.getAccountType(),
                        account.getBalance(),
                        account.getCurrency(),
                        account.getAccountHolder()));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

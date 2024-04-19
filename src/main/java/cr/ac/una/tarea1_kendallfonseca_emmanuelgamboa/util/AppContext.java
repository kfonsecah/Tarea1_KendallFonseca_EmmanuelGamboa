package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.AccountType;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Cooperative;
import javafx.collections.FXCollections;

import java.io.*;
import java.util.*;

import javafx.collections.ObservableList;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
import javafx.collections.ObservableMap;


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
    private static final ObservableList<AccountType> accountTypes = FXCollections.observableArrayList();

    private AppContext() {
        System.out.println("AppContext");
        readAssociatedsFromJsonFile();
        loadAccountTypesFromJsonFile();
    }

    // Singleton Instance Management
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

    // Property Loading
    private void cargarPropiedades() {
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

    // CRUD Operations for Associated
    public static void readAssociatedsFromJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Associated> associateds = objectMapper.readValue(new File("associateds.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Associated.class));
            System.out.println("Loaded associateds from JSON file: " + associateds);
            ObservableList<Associated> observableAsociados = FXCollections.observableArrayList(associateds);
            context.put("asociados", observableAsociados);
        } catch (IOException e) {
            System.out.println("Error reading associateds from JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String deleteAssociatedFromJsonFile(Associated associatedData) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        List<Associated.AssociatedData> associatedDataList;
        try {
            associatedDataList = objectMapper.readValue(new File("associateds.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Associated.AssociatedData.class));
        } catch (IOException e) {
            e.printStackTrace();
            return "No se pudo eliminar el usuario";
        }

        boolean deleted = associatedDataList.removeIf(data -> data.getFolio().equals(associatedData.getFolio()));

        if (deleted) {
            try {
                objectMapper.writeValue(new File("associateds.json"), associatedDataList);
            } catch (IOException e) {
                e.printStackTrace();
                return "No se pudo eliminar el usuario";
            }
        } else {
            return "No se encontró el usuario a eliminar";
        }

        return "Usuario eliminado correctamente";
    }

    public static void addAssociatedToJsonFile(Associated.AssociatedData associatedData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        List<Associated.AssociatedData> associatedDataList = new ArrayList<>();
        if (new File("associateds.json").exists()) {
            associatedDataList = objectMapper.readValue(new File("associateds.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Associated.AssociatedData.class));
        }

        for (int i = 0; i < associatedDataList.size(); i++) {
            if (associatedDataList.get(i).getFolio().equals(associatedData.getFolio())) {
                associatedDataList.set(i, associatedData);
                break;
            }
        }

        if (!associatedDataList.contains(associatedData)) {
            associatedDataList.add(associatedData);
        }

        objectMapper.writeValue(new File("associateds.json"), associatedDataList);
    }

    // CRUD Operations for Account Types
    public static void loadAccountTypesFromJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        try {
            File jsonFile = new File("account_types.json");
            if (!jsonFile.exists() || jsonFile.length() == 0) {
                System.out.println("JSON file is empty or missing.");
                List<AccountType> accountTypes = new ArrayList<>();
                ObservableList<AccountType> observableAccountTypes = FXCollections.observableArrayList(accountTypes);
                context.put("accountTypes", observableAccountTypes);
                return;
            }

            List<AccountType> accountTypes = objectMapper.readValue(jsonFile,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, AccountType.class));

            System.out.println("Loaded account types from JSON file: " + accountTypes);

            ObservableList<AccountType> observableAccountTypes = FXCollections.observableArrayList(accountTypes);
            context.put("accountTypes", observableAccountTypes);

        } catch (IOException e) {
            System.out.println("Error reading account types from JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void addAccountTypeToJsonFile(AccountType accountType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        List<AccountType> accountTypes;

        File jsonFile = new File("account_types.json");

        if (jsonFile.exists() && jsonFile.length() > 0) {
            accountTypes = objectMapper.readValue(jsonFile, new TypeReference<List<AccountType>>() {});
        } else {
            accountTypes = new ArrayList<>();
        }

        boolean found = false;
        for (int i = 0; i < accountTypes.size(); i++) {
            if (accountTypes.get(i).getName().equals(accountType.getName())) {
                accountTypes.set(i, accountType);
                found = true;
                break;
            }
        }

        if (!found) {
            accountTypes.add(accountType);
        }
        objectMapper.writeValue(jsonFile, accountTypes);
    }

    public static String deleteAccountTypeFromJsonFile(AccountType accountType) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        List<AccountType> accountTypes;
        try {
            File jsonFile = new File("account_types.json");
            accountTypes = objectMapper.readValue(jsonFile,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, AccountType.class));

            if (accountTypes.remove(accountType)) {
                objectMapper.writeValue(jsonFile, accountTypes);
                return "Tipo de cuenta eliminado exitosamente";
            } else {
                return "No se encontró el tipo de cuenta especificado";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "No se pudo eliminar el tipo de cuenta";
        }
    }

    // Getters for Associated and Account Types
    public static ObservableList<Associated> getAsociados() {
        ObservableList<Associated> associateds = (ObservableList<Associated>) context.get("asociados");
        if (associateds == null) {
            System.out.println("Asociados list is empty.");
            return FXCollections.observableArrayList();
        }
        return associateds;
    }

    public static ObservableList<AccountType> getAccountTypes() {
        ObservableList<AccountType> accountTypes = (ObservableList<AccountType>) context.get("accountTypes");
        if (accountTypes == null) {
            System.out.println("Account types list is empty.");
            return FXCollections.observableArrayList();
        }
        return accountTypes;
    }

    public void addAccountToSelectedUser(Associated selectedUser, Account account) {
        // Add the account to the selected user's list of accounts
        if (selectedUser != null) {
            selectedUser.addAccount(account);
        }
    }

    public static void updateJSONFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Convertir la lista de asociados a JSON y escribirlo en un archivo
            objectMapper.writeValue(new File("associateds.json"), asociados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


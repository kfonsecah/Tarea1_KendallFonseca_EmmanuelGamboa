package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
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



    private AppContext() {
        System.out.println("AppContext");
        readAssociatedsFromJsonFile();
        printAsociados();

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

    public void addCooperative(Cooperative cooperative) {
        cooperatives.put("cooperative", cooperative);
    }

    public Cooperative getCooperative(String name) {
        return cooperatives.get("cooperative");
    }
    public static ObservableList<Associated> getAsociados() {
        ObservableList<Associated> associateds = (ObservableList<Associated>) context.get("asociados");

        if (associateds == null) {
            System.out.println("Asociados list is empty.");
            return FXCollections.observableArrayList();
        }

        return associateds;
    }
public static void printAsociados() {
    if (asociados != null && !asociados.isEmpty()) {
        System.out.println("Asociados:");
        for (Associated asociado : asociados) {
            System.out.println(asociado);
        }
    } else {
        System.out.println("Asociados list is empty.");
    }
}

    public static ObservableList<Account> getActiveAccounts() {
        return FXCollections.observableArrayList(activeAccounts);
    }

    public static ObservableList<Account> getInactiveAccounts() {
        return FXCollections.observableArrayList(inactiveAccounts);
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



}

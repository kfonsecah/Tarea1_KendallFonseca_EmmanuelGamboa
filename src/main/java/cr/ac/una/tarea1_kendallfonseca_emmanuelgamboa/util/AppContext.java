package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;
import javafx.collections.ObservableList;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
import javafx.scene.image.Image;

import java.util.logging.Level;


public class AppContext {

    private static AppContext INSTANCE = null;

    private static final String USERS_FILE_PATH = "Asociados.txt";
    private static HashMap<String, Object> context = new HashMap<>();
    private static final ObservableList<Associated> asociados = FXCollections.observableArrayList();


    private AppContext() {
//        cargarPropiedades();
        readUsers();
        //context.put("asociados", asociados);
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
                    if (userData.length == 5) {
                        String name = userData[0];
                        String lastName = userData[1];
                        int age = Integer.parseInt(userData[2]);
                        String folio = userData[3];
                        String imageName = userData[4];

                        Associated associated = new Associated(name, lastName, age, folio, imageName);
                        asociados.add(associated);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users from the file: " + e.getMessage());
        }
        context.put("asociados", asociados);
    }
    public static ObservableList<Associated> getAsociados() {
        return asociados;
    }
}

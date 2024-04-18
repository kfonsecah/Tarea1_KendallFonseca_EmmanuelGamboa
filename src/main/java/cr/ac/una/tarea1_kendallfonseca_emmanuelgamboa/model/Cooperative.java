package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("ALL")
public class Cooperative {
    private String cooperativeName;
    private Image logo;
    private ObservableList<Associated> associatedList = FXCollections.observableArrayList();
    private AccountType accountType;


    public Cooperative() {
        createFiles();

        this.accountType = new AccountType("");

        this.accountType.setNameFromFirstInFile("account_types.txt");
    }

    public ObservableList<Associated> getAssociatedList() {
        return associatedList;
    }

    public void addAssociated(Associated associated) {
        associatedList.add(associated);
    }

    public void removeAssociated(Associated associated) {
        associatedList.remove(associated);
    }

    public String getName() {
        return cooperativeName;
    }

    public Image getLogo() {
        return logo;
    }

    public void loadFromTxtFile() {
        String filePath = "cooperativa_info.txt";
        File txtFile = new File(filePath);

        if (!txtFile.exists()) {
            try {
                txtFile.createNewFile();
                saveDefaultCooperativaInfo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (txtFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(txtFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();

                        if (key.equals("Nombre de la cooperativa")) {
                            this.cooperativeName = value;
                        } else if (key.equals("Ruta del logo")) {
                            this.logo = new Image("file:" + value);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.cooperativeName = "Nombre por defecto";
            this.logo = null;
        }
    }

    private void saveDefaultCooperativaInfo() throws IOException {
        String filePath = "cooperativa_info.txt";
        FileWriter writer = new FileWriter(filePath);
        writer.write("Nombre de la cooperativa:Nombre por defecto\n");
        writer.write("Ruta del logo: src/main/resources/cr/ac/una/tarea1_kendallfonseca_emmanuelgamboa/resources/newLogo.png");
        writer.close();
    }


    public void saveToTxtFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Nombre de la cooperativa: " + this.cooperativeName);
            writer.newLine();
            writer.write("Ruta del logo: " + (this.logo != null ? this.logo.getUrl() : ""));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   //METHOD TO GET THE NAME OF THE AccountType
    public String getAccountTypeName() {
        return accountType.getName();
    }

    public void createFiles() {
        try {
            // Create inactiveAccounts.txt file
            File inactiveAccountsFile = new File("inactiveAccounts.txt");
            if (!inactiveAccountsFile.exists()) {
                inactiveAccountsFile.createNewFile();
            }

            // Create activeAccounts.txt file
            File activeAccountsFile = new File("activeAccounts.txt");
            if (!activeAccountsFile.exists()) {
                activeAccountsFile.createNewFile();
            }

            // Create account_types.txt file
            File accountTypesFile = new File("account_types.txt");
            if (!accountTypesFile.exists()) {
                accountTypesFile.createNewFile();
            }

            // Create Associates.json file
            File associatesFile = new File("associateds.json");
            if (!associatesFile.exists()) {
                associatesFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}





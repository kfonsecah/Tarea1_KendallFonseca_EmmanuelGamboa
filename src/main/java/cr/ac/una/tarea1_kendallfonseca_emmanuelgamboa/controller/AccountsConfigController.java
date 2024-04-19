/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.AccountType;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.Mensaje;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class AccountsConfigController extends Controller implements Initializable {

    String fileName = "account_types.txt";

    @FXML
    private MFXButton btnSelect;

    @FXML
    private MFXButton btnAdd;

    @FXML
    private MFXButton btnDelete;

    @FXML
    private AnchorPane root;

    @FXML
    private Label selectedAccountType;

    @FXML
    private MFXTextField txtNewAccountType;

    @FXML
    private TableView<AccountType> tableTypesAccount;

    @FXML
    private TableColumn<AccountType, String> accountTypeColumn;

    private ObservableList<AccountType> accountTypes = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAccountTypesToTableView();
        loadFirstAccountTypeToLabel();
    }

    @Override
    public void initialize() {
    }

    @FXML
    void onActionBtnDelete(ActionEvent event) {
        AccountType selectedAccountType = tableTypesAccount.getSelectionModel().getSelectedItem();
        if (selectedAccountType != null) {
            String accountTypeName = selectedAccountType.getName();
            try {
                List<String> updatedAccountTypes = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().equals(accountTypeName)) {
                        updatedAccountTypes.add(line);
                    }
                }
                reader.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                for (String accountType : updatedAccountTypes) {
                    writer.write(accountType);
                    writer.newLine();
                }
                writer.close();

                accountTypes.remove(selectedAccountType);
                tableTypesAccount.setItems(accountTypes);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Information", root.getScene().getWindow(), "Tipo de cuenta eliminado correctamente");
            } catch (IOException e) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Error al eliminar el tipo de cuenta");
                e.printStackTrace();
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor seleccione un tipo de cuenta");
        }
    }


    @FXML
    private void onActionBtnAdd(ActionEvent event) {
        if (txtNewAccountType.getText().isEmpty()) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor rellene el campo de texto");
        } else {
            String newAccountTypeName = txtNewAccountType.getText().trim();
            if (!newAccountTypeName.isEmpty()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                    writer.write(newAccountTypeName);
                    writer.newLine();
                    writer.flush();

                    AccountType newAccountType = new AccountType(newAccountTypeName);
                    accountTypes.add(newAccountType);
                    tableTypesAccount.setItems(accountTypes);

                    txtNewAccountType.clear();

                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Information", root.getScene().getWindow(), "Tipo de cuenta agregado correctamente");
                } catch (IOException e) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Error al agregar el tipo de cuenta");
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void onActionBtnSelect(ActionEvent event) {
        AccountType selectedAccountType = tableTypesAccount.getSelectionModel().getSelectedItem();
        if (selectedAccountType != null) {
            String accountTypeName = selectedAccountType.getName();
            this.selectedAccountType.setText(selectedAccountType.getName());
            try {
                List<String> updatedAccountTypes = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = reader.readLine()) != null) {
                    updatedAccountTypes.add(line);
                }
                reader.close();

                updatedAccountTypes.remove(accountTypeName);
                updatedAccountTypes.add(0, accountTypeName);

                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                for (String accountType : updatedAccountTypes) {
                    writer.write(accountType);
                    writer.newLine();
                }
                writer.close();

                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Information", root.getScene().getWindow(), "Tipo de cuenta seleccionado correctamente");

            } catch (IOException e) {
                // Muestra el mensaje de error
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Error al seleccionar el tipo de cuenta");
                e.printStackTrace();
            }
        } else {
            // Muestra el mensaje de error
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor seleccione un tipo de cuenta");
        }
    }



    private void loadAccountTypesToTableView() {
        // Create column
        TableColumn<AccountType, String> accountTypeColumn = new TableColumn<>("Account Type");

        // Set cell value factory
        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Set column
        tableTypesAccount.getColumns().add(accountTypeColumn);

        // Load data from file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                AccountType accountType = new AccountType(line.trim());
                accountTypes.add(accountType);
            }
            tableTypesAccount.setItems(accountTypes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Listener for selecting an account type
        tableTypesAccount.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        });
    }


    private void loadFirstAccountTypeToLabel() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Lee la primera línea del archivo
            String firstLine = reader.readLine();
            if (firstLine != null) {
                // Establece el texto del Label con el primer tipo de cuenta
                selectedAccountType.setText(firstLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}



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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private MFXButton btn;

    @FXML
    private MFXButton btnAdd;

    @FXML
    private MFXButton btnDelete;

    @FXML
    private AnchorPane root;

    @FXML
    private MFXTextField txtNewAccountType;

    @FXML
    private TableView<AccountType> tableTypesAccount;

    @FXML
    private TableColumn<AccountType, String> accountTypeColumn;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAccountTypes();

    }
    @Override
    public void initialize() {
        // TODO
    }

    @FXML
    void onActionBtnCancel(ActionEvent event) {

    }
    private void saveAccountTypesToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (AccountType accountType : tableTypesAccount.getItems()) {
                writer.write(accountType.getName());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onActionBtn(ActionEvent event) {
        AccountType selectedAccountType = tableTypesAccount.getSelectionModel().getSelectedItem();
        if (selectedAccountType != null) {
            String newName = txtNewAccountType.getText().trim();
            if (!newName.isEmpty()) {
                selectedAccountType.setName(newName);
                saveAccountTypesToFile(fileName);
            }
        }
    }

    @FXML
    void onActionBtnSave(ActionEvent event) {

    }
    private void loadAccountTypes() {
        List<AccountType> accountTypes = new ArrayList<>();
        String fileName = "account_types.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    AccountType accountType = new AccountType(line.trim());
                    accountTypes.add(accountType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        tableTypesAccount.getItems().setAll(accountTypes);
    }
}



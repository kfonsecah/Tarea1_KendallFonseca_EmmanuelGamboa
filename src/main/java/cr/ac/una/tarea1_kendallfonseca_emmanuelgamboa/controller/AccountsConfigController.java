/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    private List<AccountType> accountTypes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // accountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
       // loadAccountTypes();
    }
    @Override
    public void initialize() {
        // TODO
    }

    private void loadAccountTypes() {
        accountTypes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("account_config.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String accountType = parts[1].trim();
                    accountTypes.add(new AccountType(accountType));
                }
            }
            tableTypesAccount.getItems().addAll(accountTypes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionBtnCancel(ActionEvent event) {

    }

    @FXML
    private void onActionBtn(ActionEvent event) {
        AccountType selectedAccountType = tableTypesAccount.getSelectionModel().getSelectedItem();
        if (selectedAccountType != null) {
            int index = tableTypesAccount.getSelectionModel().getSelectedIndex();
            selectedAccountType.setName(selectedAccountType.getName() + "*");
            tableTypesAccount.getItems().set(index, selectedAccountType);
        }
    }

    @FXML
    void onActionBtnSave(ActionEvent event) {

    }

}



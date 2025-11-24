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
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
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
import javafx.scene.control.cell.TextFieldTableCell;
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
    private AnchorPane root;

    @FXML
    private MFXTextField txtNewAccountType;

    @FXML
    private TableView<AccountType> tableTypesAccount;

    @FXML
    private TableColumn<AccountType, String> columnName;

    @FXML
    private MFXButton btnAdd;

    @FXML
    private MFXButton btnDelete;


    private ObservableList<AccountType> accountTypes;

    private AppContext appContext;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        appContext = AppContext.getInstance();
        loadAccountTypes();


    }

    @Override
    public void initialize() {


    }

    private void loadAccountTypes() {
        accountTypes = appContext.getAccountTypes();
        if (accountTypes == null) {
            appContext.loadAccountTypesFromJsonFile();
            accountTypes = appContext.getAccountTypes();
        }
        tableTypesAccount.setItems(accountTypes);
    }

    private void initializeTableView() {

    }


    @FXML
    void onActionBtnDelete(ActionEvent event) {
        AccountType selectedAccountType = tableTypesAccount.getSelectionModel().getSelectedItem();
        if (selectedAccountType != null) {
            appContext.deleteAccountTypeFromJsonFile(selectedAccountType);
            accountTypes.remove(selectedAccountType);
            tableTypesAccount.setItems(accountTypes);
        } else
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor seleccione un tipo de cuenta para eliminar.");
    }


    @FXML
    void onActionBtnAdd(ActionEvent event) {
        String newAccountTypeName = txtNewAccountType.getText().trim();
        if (!newAccountTypeName.isEmpty()) {
            AccountType newAccountType = new AccountType(newAccountTypeName);
            try {
                appContext.addAccountTypeToJsonFile(newAccountType);
                accountTypes.add(newAccountType);
                txtNewAccountType.clear();
            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor ingrese un nombre para el tipo de cuenta.");
        }
    }


}




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author kfonsecah
 */
public class OpenAccountsController extends Controller implements Initializable {

    @FXML
    private TableView<Account> activeAccounts;

    @FXML
    private TableView<Account> pendingAccounts;

    @FXML
    private AnchorPane root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initializeTableColumns();

      
        loadAccountsToTable();
    }
    @Override
    public void initialize() {
    }

    private void initializeTableColumns() {
        // Columnas de la tabla de cuentas activas
        TableColumn<Account, String> activeAccountNumberColumn = new TableColumn<>("Account Number");
        activeAccountNumberColumn.setCellValueFactory(cellData -> cellData.getValue().accountNumberProperty());

        TableColumn<Account, String> activeAccountTypeColumn = new TableColumn<>("Account Type");
        activeAccountTypeColumn.setCellValueFactory(cellData -> cellData.getValue().accountTypeProperty());

        // Agregar las columnas a la tabla de cuentas activas
        activeAccounts.getColumns().addAll(activeAccountNumberColumn, activeAccountTypeColumn);

        // Columnas de la tabla de cuentas pendientes
        TableColumn<Account, String> pendingAccountNumberColumn = new TableColumn<>("Account Number");
        pendingAccountNumberColumn.setCellValueFactory(cellData -> cellData.getValue().accountNumberProperty());

        TableColumn<Account, String> pendingAccountTypeColumn = new TableColumn<>("Account Type");
        pendingAccountTypeColumn.setCellValueFactory(cellData -> cellData.getValue().accountTypeProperty());

        // Agregar las columnas a la tabla de cuentas pendientes
        pendingAccounts.getColumns().addAll(pendingAccountNumberColumn, pendingAccountTypeColumn);
    }

    private void loadAccountsToTable() {
        ObservableList<Account> accounts = AppContext.getInstance().getAccounts();

        for (Account account : accounts) {
            if (account.isActive()) {
                activeAccounts.getItems().add(account);
            } else {
                pendingAccounts.getItems().add(account);
            }
        }
    }
}


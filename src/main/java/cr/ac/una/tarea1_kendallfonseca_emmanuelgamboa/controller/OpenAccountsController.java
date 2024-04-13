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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
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

        enableDragAndDrop(activeAccounts, true);


        loadAccountsToTable();
    }

    @Override
    public void initialize() {
    }

    private void initializeTableColumns() {
        activeAccounts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        pendingAccounts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
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

    private void enableDragAndDrop(TableView<Account> tableView, boolean isActive) {
        tableView.setOnDragDetected(event -> {
            ObservableList<Account> selectedItems = tableView.getSelectionModel().getSelectedItems();
            if (selectedItems != null && !selectedItems.isEmpty()) {
                Dragboard dragboard = tableView.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.put(DataFormat.PLAIN_TEXT, ""); // El contenido no se usa pero es necesario
                dragboard.setContent(content);
                event.consume();
            }
        });

        tableView.setOnDragOver(event -> {
            if (event.getGestureSource() != tableView && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        tableView.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;
            if (dragboard.hasString()) {
                ObservableList<Account> selectedItems = tableView.getSelectionModel().getSelectedItems();
                Account accountToMove = selectedItems.get(0); // Solo necesitamos mover el primer elemento seleccionado
                if (isActive) {
                    pendingAccounts.getItems().remove(accountToMove);
                    activeAccounts.getItems().add(accountToMove);
                    accountToMove.setActive(true);
                } else {
                    activeAccounts.getItems().remove(accountToMove);
                    pendingAccounts.getItems().add(accountToMove);
                    accountToMove.setActive(false);
                }
                // Aquí debes actualizar el archivo de texto con el estado actualizado
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }
}


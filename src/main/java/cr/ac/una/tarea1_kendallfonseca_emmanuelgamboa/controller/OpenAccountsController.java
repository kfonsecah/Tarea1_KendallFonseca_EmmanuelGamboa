package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.AccountType;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AccountUser;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;

import javafx.collections.ObservableList;
import javafx.scene.control.*;

public class OpenAccountsController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ListView<AccountType> accountTypes;

    @FXML
    private MFXButton btnSearch;

    @FXML
    private MFXComboBox<String> comboBoxFilter;

    @FXML
    private ListView<Account> userAccounts;

    @FXML
    private MFXTextField txtSearch;

    @FXML
    private TableView<Associated> userSearchList;


    AppContext appContext = AppContext.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {



        ObservableList<Associated> asociados = AppContext.getAsociados();
        ObservableList<AccountType> tiposCuenta = AppContext.getAccountTypes();


        accountTypes.setItems(tiposCuenta);
        loadUsersToTableView();
        comboBoxFilter.getItems().addAll("Todo", "Nombre", "Apellido", "Folio", "Edad");
        comboBoxFilter.setValue("Todo");

        userSearchList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadAssociatedAccounts(newValue);
            }
        });

    }

    private void loadAssociatedAccounts(Associated associated) {

        AccountUser accountUser = new AccountUser();
        ObservableList<Account> associatedAccounts = accountUser.getAccountsByFolio(associated.getFolio());

        // Verificamos si hay cuentas asociadas
        if (associatedAccounts.isEmpty()) {
            System.out.println("No hay cuentas asociadas al usuario con folio: " + associated.getFolio());
            userAccounts.getItems().clear();

        } else {
            // Limpiamos la lista de cuentas de usuario y agregamos las cuentas asociadas
            userAccounts.getItems().clear();
            userAccounts.getItems().addAll(associatedAccounts);
        }
        updateTableView();
    }

    public void initialize() {
    }

    private void loadUsersToTableView() {
        TableColumn<Associated, String> folioColumn = new TableColumn<>("Folio");
        TableColumn<Associated, String> nameColumn = new TableColumn<>("Nombre");
        TableColumn<Associated, String> lastNameColumn = new TableColumn<>("Apellido");
        TableColumn<Associated, Integer> ageColumn = new TableColumn<>("Edad");

        folioColumn.setCellValueFactory(new PropertyValueFactory<>("assoFolio"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("assoName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("assoLastName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("assoAge"));

        userSearchList.getColumns().addAll(folioColumn, nameColumn, lastNameColumn, ageColumn);

        ObservableList<Associated> asociados = AppContext.getAsociados();
        userSearchList.setItems(asociados);
    }

    @FXML
    private void onActionBtnSearch(ActionEvent event) {
        String filter = comboBoxFilter.getValue();
        if (!txtSearch.getText().isEmpty()) {
            ObservableList<Associated> filteredData = FXCollections.observableArrayList();
            ObservableList<Associated> list = AppContext.getAsociados();

            for (Associated data : list) {
                if ((filter.equals("Todo") && data.getAssoName().toLowerCase().contains(txtSearch.getText().toLowerCase())) ||
                        data.getAssoLastName().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                        data.getAssoFolio().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                        Integer.toString(data.getAssoAge()).contains(txtSearch.getText())) {
                    filteredData.add(data);
                } else if (filter.equals("Nombre") && data.getAssoName().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                    filteredData.add(data);
                } else if (filter.equals("Apellido") && data.getAssoLastName().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                    filteredData.add(data);
                } else if (filter.equals("Folio") && data.getAssoFolio().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                    filteredData.add(data);
                } else if (filter.equals("Edad") && Integer.toString(data.getAssoAge()).contains(txtSearch.getText())) {
                    filteredData.add(data);
                }
            }
            userSearchList.setItems(filteredData);
        } else {
            updateTableView();
        }
    }

    private void updateTableView() {
        ObservableList<Associated> asociados = AppContext.getAsociados();
        int selectedIndex = userSearchList.getSelectionModel().getSelectedIndex();
        userSearchList.setItems(asociados);
        userSearchList.getSelectionModel().select(selectedIndex);
        userSearchList.refresh();
    }

    @FXML
    public void onDragDetectedAccountTypes(MouseEvent event) {
        AccountType selectedAccountType = accountTypes.getSelectionModel().getSelectedItem();
        if (selectedAccountType != null) {
            Dragboard dragboard = accountTypes.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedAccountType.getName());
            dragboard.setContent(content);
            event.consume();
        }
    }

    @FXML
    public void onDragOverAccountTypes(DragEvent event) {
        if (event.getGestureSource() != event.getTarget() && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
    }

    @FXML
    public void onDragDroppedAccountTypes(DragEvent event) throws IOException {
        Dragboard dragboard = event.getDragboard();
        boolean success = false;

        if (dragboard.hasString()) {
            String accountTypeName = dragboard.getString();
            int selectedIndex = userSearchList.getSelectionModel().getSelectedIndex();
            Associated associated = userSearchList.getItems().get(selectedIndex);

            Account accountToRemove = new Account(0.0, "Colones", accountTypeName, associated.getAssoName(), associated.getAssoFolio());

            // Remove the account from the JSON file
            AppContext.removeAccountFromJsonFile(accountToRemove);

            // Remove the account from the table view
            userAccounts.getItems().remove(accountToRemove);
            updateListView();
            success = true;
        }

        event.setDropCompleted(success);
    }

    @FXML
    public void onDragDetectedUserAccounts(MouseEvent event) {
        Account selectedAccount = userAccounts.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) {
            Dragboard dragboard = userAccounts.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedAccount.getAccountType());
            dragboard.setContent(content);
            event.consume();
        }
    }

    @FXML
    public void onDragOverUserAccounts(DragEvent event) {
        if (event.getGestureSource() != userAccounts && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
    }

    @FXML
    public void onDragDroppedUserAccounts(DragEvent event) throws IOException {
        Dragboard dragboard = event.getDragboard();
        boolean success = false;

        if (dragboard.hasString()) {
            String accountTypeName = dragboard.getString();
            int selectedIndex = userSearchList.getSelectionModel().getSelectedIndex();
            Associated associated = userSearchList.getItems().get(selectedIndex);

            Account newAccount = new Account(0.0, "Colones", accountTypeName, associated.getAssoName(), associated.getAssoFolio());
            appContext.addAccountToJsonFile(newAccount);

            // Agregar la nueva cuenta a la lista de cuentas asociadas
            AccountUser accountUser = new AccountUser();
            ObservableList<Account> associatedAccounts = accountUser.getAccountsByFolio(associated.getFolio());
            associatedAccounts.add(newAccount);

            // Limpiar la lista de cuentas de usuario y agregar las cuentas actualizadas
            userAccounts.getItems().clear();
            userAccounts.getItems().addAll(associatedAccounts);

            success = true;
            updateListView();
        }

        event.setDropCompleted(success);
    }


    private void updateListView() {
        // Get the accounts of the selected user
        int selectedIndex = userSearchList.getSelectionModel().getSelectedIndex();
        Associated associated = userSearchList.getItems().get(selectedIndex);
        AccountUser accountUser = new AccountUser();
        ObservableList<Account> associatedAccounts = accountUser.getAccountsByFolio(associated.getFolio());

        // Update the list of accounts
        for (Account updatedAccount : AppContext.getAccounts()) {
            boolean found = false;
            for (Account account : associatedAccounts) {
                if (updatedAccount.getAccountType().equals(account.getAccountType())) {
                    // If the account exists, update it
                    account.setBalance(updatedAccount.getBalance());
                    account.setCurrency(updatedAccount.getCurrency());
                    account.setAccountType(updatedAccount.getAccountType());
                    found = true;
                    break;
                }
            }
            if (!found) {
                // If the account does not exist, add it to the list
                associatedAccounts.add(updatedAccount);
            }
        }

        // Refresh the ListView to reflect the changes
        userAccounts.refresh();
    }


}



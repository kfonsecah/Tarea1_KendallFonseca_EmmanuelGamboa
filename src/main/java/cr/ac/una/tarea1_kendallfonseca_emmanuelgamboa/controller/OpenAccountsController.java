package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.AccountType;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
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

    private AccountType draggedAccountType;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppContext appContext = AppContext.getInstance();
        ObservableList<Associated> asociados = AppContext.getAsociados();
        ObservableList<AccountType> tiposCuenta = AppContext.getAccountTypes();
        Account account = new Account("Cuenta de ahorros", 0, "CRC", "Kendall Fonseca");
        try {
            appContext.addAccountToAssociatedInJsonFile("KF0789",account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        accountTypes.setItems(tiposCuenta);
        loadUsersToTableView();
        comboBoxFilter.getItems().addAll("Todo", "Nombre", "Apellido", "Folio", "Edad");
        comboBoxFilter.setValue("Todo");

        accountTypes.setOnDragDetected(event -> {
            draggedAccountType = accountTypes.getSelectionModel().getSelectedItem();
            if (draggedAccountType != null) {
                Dragboard dragboard = accountTypes.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(draggedAccountType.getName());
                dragboard.setContent(content);
                event.consume();
            }
        });

        userAccounts.setOnDragOver(event -> {
            if (event.getGestureSource() != userAccounts && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        userAccounts.setOnDragDropped(event -> {
            String accountType = event.getDragboard().getString();
            Associated selectedAssociated = userSearchList.getSelectionModel().getSelectedItem();
            if (selectedAssociated != null) {
                Account newAccount = new Account(accountType, 0, "CRC", selectedAssociated.getName());
                AppContext.getInstance().addAccountToSelectedUser(newAccount);
                ObservableList<Account> userAccountList = FXCollections.observableArrayList(selectedAssociated.getAccounts());
                userAccounts.setItems(userAccountList);
            }
            event.setDropCompleted(true);
            event.consume();
        });
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
    void onDragDetected(MouseEvent event) {
        if (userAccounts.getSelectionModel().getSelectedItem() != null) {
            Account selectedAccount = userAccounts.getSelectionModel().getSelectedItem();
            Dragboard dragboard = userAccounts.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(selectedAccount.getAccountType());
            dragboard.setContent(content);
            event.consume();
        }
    }

    @FXML
    void onDragOver(DragEvent event) {
        if (event.getGestureSource() != userAccounts && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    @FXML
    void onDragDropped(DragEvent event) {
        String accountType = event.getDragboard().getString();
        Associated selectedAssociated = userSearchList.getSelectionModel().getSelectedItem();
        if (selectedAssociated != null) {
            Account newAccount = new Account( accountType, 0, "CRC", selectedAssociated.getName());
            if (AppContext.getInstance().addAccountToSelectedUser(newAccount)) {
                System.out.println("New account added successfully.");
                ObservableList<Account> userAccountList = FXCollections.observableArrayList(selectedAssociated.getAccounts());
                userAccounts.setItems(userAccountList);
            } else {
                System.out.println("Failed to add new account.");
            }
        } else {
            System.out.println("No associated user selected.");
        }
        event.setDropCompleted(true);
        event.consume();
    }

    @FXML
    public void onDragDetectedAccountTypes(MouseEvent event) {
        draggedAccountType = accountTypes.getSelectionModel().getSelectedItem();
        if (draggedAccountType != null) {
            Dragboard dragboard = accountTypes.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(draggedAccountType.getName());
            dragboard.setContent(content);
            event.consume();
        }
    }

    @FXML
    public void onDragOverAccountTypes(DragEvent event) {
        if (event.getGestureSource() != accountTypes && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    @FXML
    public void onDragDroppedAccountTypes(DragEvent event) {
        String accountType = event.getDragboard().getString();
        // Implement your logic here for when an account type is dropped onto another area
        event.setDropCompleted(true);
        event.consume();
    }

    @FXML
    public void onDragDetectedUserAccounts(MouseEvent event) {
        if (userAccounts.getSelectionModel().getSelectedItem() != null) {
            Account selectedAccount = userAccounts.getSelectionModel().getSelectedItem();
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
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    @FXML
    public void onDragDroppedUserAccounts(DragEvent event) {
        String accountType = event.getDragboard().getString();
        Associated selectedAssociated = userSearchList.getSelectionModel().getSelectedItem();
        if (selectedAssociated != null) {
            Account newAccount = new Account(accountType, 0, "CRC", selectedAssociated.getName());
            if (AppContext.getInstance().addAccountToSelectedUser(newAccount)) {
                System.out.println("New account added successfully.");
                ObservableList<Account> userAccountList = FXCollections.observableArrayList(selectedAssociated.getAccounts());
                userAccounts.setItems(userAccountList);
            } else {
                System.out.println("Failed to add new account.");
            }
        } else {
            System.out.println("No associated user selected.");
        }
        event.setDropCompleted(true);
        event.consume();
    }

}






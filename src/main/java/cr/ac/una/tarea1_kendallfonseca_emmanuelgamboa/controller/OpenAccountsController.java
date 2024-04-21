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

    private AccountType draggedAccountType;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppContext appContext = AppContext.getInstance();
        ObservableList<Associated> asociados = AppContext.getAsociados();
        ObservableList<AccountType> tiposCuenta = AppContext.getAccountTypes();

        Account Account = new Account(4.5, "Colones", "REG", "LD0819");

        try {
            appContext.addAccountToJsonFile(Account);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
        // Creamos una instancia de AccountUser y obtenemos las cuentas asociadas al asociado
        AccountUser accountUser = new AccountUser();
        ObservableList<Account> associatedAccounts = accountUser.getAccountsByFolio(associated.getFolio());

        // Verificamos si hay cuentas asociadas
        if (associatedAccounts.isEmpty()) {
            System.out.println("No hay cuentas asociadas al usuario con folio: " + associated.getFolio());
            // Aquí puedes agregar código para mostrar una notificación en la interfaz de usuario si lo deseas
        } else {
            // Limpiamos la lista de cuentas de usuario y agregamos las cuentas asociadas
            userAccounts.getItems().clear();
            userAccounts.getItems().addAll(associatedAccounts);
        }
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

    }

    @FXML
    void onDragOver(DragEvent event) {

    }

    @FXML
    void onDragDropped(DragEvent event) {

    }

    @FXML
    public void onDragDetectedAccountTypes(MouseEvent event) {

    }

    @FXML
    public void onDragOverAccountTypes(DragEvent event) {

    }

    @FXML
    public void onDragDroppedAccountTypes(DragEvent event) {

    }

    @FXML
    public void onDragDetectedUserAccounts(MouseEvent event) {


    }

    @FXML
    public void onDragOverUserAccounts(DragEvent event) {}





    @FXML
    public void onDragDroppedUserAccounts(DragEvent event) {}




}






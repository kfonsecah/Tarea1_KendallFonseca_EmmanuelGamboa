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
    public void onFilterButtonClick(ActionEvent event) {
        ObservableList<Associated> filteredData = FXCollections.observableArrayList();
        String filter = String.valueOf(accountTypes.getSelectionModel().getSelectedItem());
        String txtSearchValue = txtSearch.getText();

        if (!txtSearchValue.isEmpty()) {
            for (Associated data : userSearchList.getItems()) {
                if (filter.equals("Nombre") && data.getAssoName().toLowerCase().contains(txtSearchValue.toLowerCase())) {
                    filteredData.add(data);
                } else if (filter.equals("Folio") && data.getAssoFolio().toLowerCase().contains(txtSearchValue.toLowerCase())) {
                    filteredData.add(data);
                } else if (filter.equals("Edad") && Integer.toString(data.getAssoAge()).contains(txtSearchValue)) {
                    filteredData.add(data);
                }
            }
            userSearchList.setItems(filteredData);
        } else {
            updateTableView();
        }
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
    public void onDragDroppedAccountTypes(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        boolean success = false;
        if (dragboard.hasString()) {
            String accountTypeName = dragboard.getString();
            Associated selectedAssociated = userSearchList.getSelectionModel().getSelectedItem();
            if (selectedAssociated != null) {
                // Obtener el folio del usuario seleccionado
                String folio = selectedAssociated.getFolio();
                // Crear una nueva instancia de cuenta con el folio del usuario seleccionado
                Account newAccount = new Account(0.0, "Colones", accountTypeName, selectedAssociated.getName(), folio);
                // Agregar la nueva cuenta a la lista de cuentas
                userAccounts.getItems().add(newAccount);
                // Guardar la lista actualizada en el archivo JSON
                try {
                    AppContext.addAccountToJsonFile(newAccount);
                } catch (IOException e) {
                    e.printStackTrace();
                    // Manejar cualquier error de escritura en el archivo JSON
                }
                success = true;
            }
        }
        event.setDropCompleted(success);
        event.consume();
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
            success = true;
        }

        event.setDropCompleted(success);

        updateTableView();
    }





}






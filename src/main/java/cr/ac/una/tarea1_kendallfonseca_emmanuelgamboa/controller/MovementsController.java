package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Deposits;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AccountUser;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MovementsController extends Controller implements Initializable {

    @FXML
    private TableView<Deposits> TableViewMovements;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Account> tableViewAccounts;

    @FXML
    private Label txtAge;

    @FXML
    private Label txtFolio;

    @FXML
    private MFXTextField txtSearch;

    @FXML
    private Label txtLastName;

    @FXML
    private Label txtName;

    @FXML
    private ImageView userPhoto;

    @FXML
    void onActionBtnSearch(ActionEvent event) {
        AccountUser accountUser = new AccountUser();
        String folio = txtSearch.getText();
        if (!folio.isEmpty()) {
            Associated associated = AppContext.getInstance().getAssociatedByFolio(folio);
            if (associated != null) {
                txtName.setText(associated.getAssoName());
                txtLastName.setText(associated.getAssoLastName());
                txtAge.setText(String.valueOf(associated.getAssoAge()));
                txtFolio.setText(associated.getAssoFolio());

                // Cargar la imagen del usuario
                String photoPath = "userphotos/" + associated.getAssoFolio() + ".png";
                Image image = new Image(new File(photoPath).toURI().toString());
                userPhoto.setImage(image);

                // Cargar las cuentas del usuario en la tableViewAccounts
                ObservableList<Account> userAccounts = accountUser.getAccountsByFolio(folio);
                tableViewAccounts.setItems(userAccounts);
            } else {

                new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "No hay una cuenta asociada a este usuario");
                clearFields();
            }
        } else {
            clearFields();
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtLastName.setText("");
        txtAge.setText("");
        txtFolio.setText("");
        userPhoto.setImage(null);
        tableViewAccounts.getItems().clear();
        TableViewMovements.getItems().clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtSearch.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String character = event.getCharacter();
            // Verificar si el caracter es una letra minuscula
            if (character.matches("[a-z]")) {
                // Convertir el caracter a mayúscula
                String upperCaseChar = character.toUpperCase();
                txtSearch.setText(txtSearch.getText() + upperCaseChar);
                event.consume();
            }
        });
        // Configurar las columnas de la TableViewMovements
        TableColumn<Deposits, String> dateTimeColumn = new TableColumn<>("Fecha y Hora");
        TableColumn<Deposits, Integer> monedaColumn = new TableColumn<>("Moneda");
        TableColumn<Deposits, String> folioColumn = new TableColumn<>("Folio");
        TableColumn<Deposits, String> tipoCuentaColumn = new TableColumn<>("Tipo de Cuenta");
        TableColumn<Deposits, String> tipoMovimientoColumn = new TableColumn<>("Tipo de Movimiento");

        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTimeString"));
        monedaColumn.setCellValueFactory(new PropertyValueFactory<>("moneda"));
        folioColumn.setCellValueFactory(new PropertyValueFactory<>("folio"));
        tipoCuentaColumn.setCellValueFactory(new PropertyValueFactory<>("tipoCuenta"));
        tipoMovimientoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoMovimiento"));


        TableColumn<Account, String> accountTypeColumn = new TableColumn<>("Account Type");

        TableColumn<Account, String> balanceColumn = new TableColumn<>("Balance");
        TableColumn<Account, String> currencyColumn = new TableColumn<>("Currency");

        TableColumn<Account, String> accountHolderColumn = new TableColumn<>("Account Holder");

        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        currencyColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));
        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        accountHolderColumn.setCellValueFactory(new PropertyValueFactory<>("accountHolder"));
        folioColumn.setCellValueFactory(new PropertyValueFactory<>("folio"));

        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("accountType"));

        tableViewAccounts.getColumns().addAll(balanceColumn, accountTypeColumn, currencyColumn, accountHolderColumn);

        TableViewMovements.getColumns().addAll(dateTimeColumn, monedaColumn, folioColumn, tipoCuentaColumn, tipoMovimientoColumn);

        tableViewAccounts.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedAccount) -> {
            if (selectedAccount != null) {
                String folio = selectedAccount.getFolio();
                String tipoCuentaSeleccionado = selectedAccount.getAccountType();
                try {
                    AccountUser accountUser = new AccountUser();
                    ObservableList<Deposits> movements = accountUser.getAccountMovements(folio, tipoCuentaSeleccionado);
                    TableViewMovements.setItems(movements);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public void initialize() {
    }



    @FXML
    void onActionDetail(ActionEvent event) {
        TableViewMovements.getSelectionModel().clearSelection();
        TableViewMovements.getColumns().clear();

        TableColumn<Deposits, String> dateTimeColumn = new TableColumn<>("Fecha y Hora");
        TableColumn<Deposits, Integer> monedaColumn = new TableColumn<>("Moneda");
        TableColumn<Deposits, String> folioColumn = new TableColumn<>("Folio");
        TableColumn<Deposits, String> tipoCuentaColumn = new TableColumn<>("Tipo de Cuenta");
        TableColumn<Deposits, String> tipoMovimientoColumn = new TableColumn<>("Tipo de Movimiento");

        // Asignar las propiedades de celda para mostrar la fecha y hora, moneda, folio, tipo de cuenta y tipo de movimiento
        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTimeString"));
        monedaColumn.setCellValueFactory(new PropertyValueFactory<>("moneda"));
        folioColumn.setCellValueFactory(new PropertyValueFactory<>("folio"));
        tipoCuentaColumn.setCellValueFactory(new PropertyValueFactory<>("tipoCuenta"));
        tipoMovimientoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoMovimiento"));

        // Agregar las columnas a la TableViewMovements
        TableViewMovements.getColumns().addAll(dateTimeColumn, monedaColumn, folioColumn, tipoCuentaColumn, tipoMovimientoColumn);

        // Obtener la lista de movimientos seleccionados
        ObservableList<Deposits> selectedMovements = TableViewMovements.getItems();

        // Limpiar la selección actual
        TableViewMovements.getSelectionModel().clearSelection();

        // Seleccionar todos los movimientos
        TableViewMovements.getSelectionModel().selectAll();
    }

    @FXML
    void onActionResumed(ActionEvent event) {
        // Limpiar cualquier selección previa
        TableViewMovements.getSelectionModel().clearSelection();

        // Eliminar todas las columnas existentes
        TableViewMovements.getColumns().clear();

        TableColumn<Deposits, Integer> monedaColumn = new TableColumn<>("Moneda");
        TableColumn<Deposits, String> tipoCuentaColumn = new TableColumn<>("Tipo de Cuenta");
        TableColumn<Deposits, String> tipoMovimientoColumn = new TableColumn<>("Tipo de Movimiento");

        monedaColumn.setCellValueFactory(new PropertyValueFactory<>("moneda"));
        tipoCuentaColumn.setCellValueFactory(new PropertyValueFactory<>("tipoCuenta"));
        tipoMovimientoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoMovimiento"));

        TableViewMovements.getColumns().addAll(monedaColumn, tipoCuentaColumn, tipoMovimientoColumn);

        ObservableList<Deposits> selectedMovements = TableViewMovements.getItems();

        TableViewMovements.getSelectionModel().clearSelection();

        TableViewMovements.getSelectionModel().selectAll();
    }

}

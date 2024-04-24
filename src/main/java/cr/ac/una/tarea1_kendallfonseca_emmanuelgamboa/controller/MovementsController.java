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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        // Configurar las columnas de la TableViewMovements
        TableColumn<Deposits, String> dateTimeColumn = new TableColumn<>("Fecha y Hora");
        TableColumn<Deposits, Integer> monedaColumn = new TableColumn<>("Moneda");
        TableColumn<Deposits, String> folioColumn = new TableColumn<>("Folio");
        TableColumn<Deposits, String> tipoCuentaColumn = new TableColumn<>("Tipo de Cuenta");
        TableColumn<Deposits, String> tipoMovimientoColumn = new TableColumn<>("Tipo de Movimiento");

        // Asignar los valores de las propiedades de Deposits a las columnas
        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTimeString"));
        monedaColumn.setCellValueFactory(new PropertyValueFactory<>("moneda"));
        folioColumn.setCellValueFactory(new PropertyValueFactory<>("folio"));
        tipoCuentaColumn.setCellValueFactory(new PropertyValueFactory<>("tipoCuenta"));
        tipoMovimientoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoMovimiento"));

        TableColumn<Account, String> accountNumberColumn = new TableColumn<>("Account Number");
        TableColumn<Account, String> accountTypeColumn = new TableColumn<>("Account Type");
        // Add more columns as needed

        // Bind cell value factories to properties of the Account class
        accountNumberColumn.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        // Add more cell value factories for additional columns

        // Add columns to the tableViewAccounts
        tableViewAccounts.getColumns().addAll(accountNumberColumn, accountTypeColumn);

        // Agregar las columnas a la TableViewMovements
        TableViewMovements.getColumns().addAll(dateTimeColumn, monedaColumn, folioColumn, tipoCuentaColumn, tipoMovimientoColumn);

        // Establecer un listener para cargar los movimientos al seleccionar una cuenta
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

    }

    @FXML
    void onActionResumed(ActionEvent event) {

    }

}

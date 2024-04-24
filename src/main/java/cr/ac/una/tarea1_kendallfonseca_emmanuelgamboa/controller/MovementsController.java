package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Deposits;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AccountUser;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import io.github.palexdev.materialfx.controls.MFXButton;
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
    private MFXButton btnSearch;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Account> tableViewAccounts;

    @FXML
    private Label txtAge;

    @FXML
    private Label txtFolio;

    @FXML
    private Label txtLastName;

    @FXML
    private Label txtName;

    @FXML
    private MFXTextField txtSearch;


    @FXML
    private TableColumn<Deposits, Double> movementsColumn;




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
                tableViewAccounts.setItems(accountUser.getAccountsByFolio(folio));

                // Obtener los movimientos asociados al folio y tipo de cuenta seleccionado
                String tipoCuentaSeleccionado = ""; // Obtén el tipo de cuenta seleccionado
                ObservableList<Deposits> movements = null;
                try {
                    movements = accountUser.getAccountMovements(folio, tipoCuentaSeleccionado);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Establecer los movimientos en la otra TableView
                movementsColumn.setCellValueFactory(new PropertyValueFactory<>("amount")); // Ajusta el nombre de la propiedad según corresponda
                TableViewMovements.setItems(movements);
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
    }

    @FXML
    void onActionDetail(ActionEvent event) {

    }

    @FXML
    void onActionResumed(ActionEvent event) {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<Account, String> colAccountNumber = new TableColumn<>("Número de Cuenta");
        TableColumn<Account, String> colBalance = new TableColumn<>("Saldo");
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        tableViewAccounts.getColumns().addAll(colAccountNumber, colBalance);
    }
    @Override
    public void initialize() {

    }
}

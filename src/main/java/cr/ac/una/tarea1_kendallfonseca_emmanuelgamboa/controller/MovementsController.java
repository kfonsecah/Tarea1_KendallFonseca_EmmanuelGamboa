/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Deposits;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AccountUser;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author gambo
 */
public class MovementsController extends Controller implements Initializable {

    @FXML
    private TableView<Account> tableViewAccounts;

    @FXML
    private TableView<Deposits> tableViewMovements;

    @FXML
    private MFXTextField txtSearch;

    @FXML
    private Label txtName;

    @FXML
    private Label txtLastName;

    @FXML
    private Label txtAge;

    @FXML
    private Label txtFolio;

    @FXML
    private MFXButton btnSearch;

    @FXML
    private AnchorPane root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    @Override
    public void initialize() {
        // TODO
    }

    @FXML
    void onActionBtnSearch(ActionEvent event) {
        String folio = txtSearch.getText();
        if (!folio.isEmpty()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                File file = new File("associateds.json");
                if (file.exists()) {
                    List<Associated.AssociatedData> associatedDataList = objectMapper.readValue(file, new TypeReference<List<Associated.AssociatedData>>() {});
                    for (Associated.AssociatedData userData : associatedDataList) {
                        if (userData.getFolio().equals(folio)) {
                            txtName.setText(userData.getName());
                            txtLastName.setText(userData.getLastName());
                            txtAge.setText(String.valueOf(userData.getAge()));
                            txtFolio.setText(userData.getFolio());
                            // Cargar las cuentas asociadas al usuario en la TableView
                            AccountUser accountUser = new AccountUser();
                            ObservableList<Account> userAccounts = accountUser.getAccountsByFolio(folio);
                            tableViewAccounts.setItems(userAccounts);
                            return;
                        }
                    }
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "No se encontró ningún usuario con el folio proporcionado.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "No se encontró el archivo de usuarios asociados.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Error al leer el archivo de usuarios asociados.");
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor, ingrese un folio antes de continuar.");
        }
    }

    @FXML
    void onActionDetail(ActionEvent event) throws IOException {
        Account selectedAccount = tableViewAccounts.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) {
            String folio = selectedAccount.getFolio();
            String accountType = selectedAccount.getAccountType();
            AccountUser accountUser = new AccountUser();
            ObservableList<Deposits> accountMovements = accountUser.getAccountMovements(folio, accountType);
            tableViewMovements.setItems(accountMovements);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor, seleccione una cuenta antes de ver los movimientos.");
        }
    }

    @FXML
    void onActionResumed(ActionEvent event) {

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.AccountType;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.Mensaje;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class AccountsConfigController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private MFXTextField txtNewAccountType;

    @FXML
    private Label selectedAccountType;

    @FXML
    private TableView<AccountType> tableTypesAccount;

    @FXML
    private MFXButton btnAdd;

    @FXML
    private MFXButton btnDelete;

    @FXML
    private MFXButton btnSelect;

    private ObservableList<AccountType> accountTypes;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        accountTypes = FXCollections.observableArrayList();
        tableTypesAccount.setItems(accountTypes);


    }

    @Override
    public void initialize() {


    }

    private void initializeTableView() {
        // Create the table columns
        TableColumn<AccountType, String> columnType = new TableColumn<>("Tipo de cuenta");
        TableColumn<AccountType, String> columnDescription = new TableColumn<>("Descripción");

        // Set the cell value factory for each column
        columnType.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        //columnDescription.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDescription()));

        // Add the columns to the table view
        tableTypesAccount.getColumns().addAll(columnType, columnDescription);

        // Make the table view editable
        tableTypesAccount.setEditable(true);

        // Use TextFieldTableCell for editing
        columnType.setCellFactory(TextFieldTableCell.forTableColumn());
        columnDescription.setCellFactory(TextFieldTableCell.forTableColumn());

        // Add listener for commit changes on TextFieldTableCell
        columnType.setOnEditCommit(event -> {
            // Get the selected account type from the table view
            AccountType selectedAccountType = event.getRowValue();

            // Update the name of the selected account type
            selectedAccountType.setName(event.getNewValue());
        });
        columnDescription.setOnEditCommit(event -> {
            // Get the selected account type from the table view
            AccountType selectedAccountType = event.getRowValue();

            // Update the description of the selected account type
            //selectedAccountType.setDescription(event.getNewValue());
        });

        // Load the account types to the table view
        tableTypesAccount.setItems(accountTypes);
    }


    @FXML
    void onActionBtnDelete(ActionEvent event) {
        // Get the selected account type from the table view
        AccountType selectedAccountType = tableTypesAccount.getSelectionModel().getSelectedItem();

        // Check if an account type is selected
        if (selectedAccountType != null) {
            // Remove the selected account type from the observable list
            accountTypes.remove(selectedAccountType);

            // Show success message
          //  new Mensaje("Exito", "El tipo de cuenta ha sido eliminado exitosamente", Mensaje.SUCCESS);
        } else {
            // Show error message
          //  new Mensaje("Error", "Debe seleccionar un tipo de cuenta para eliminarlo", Mensaje.ERROR);
        }
    }

    @FXML
    void onActionBtnSelect(ActionEvent event) {

    }

    @FXML
    void onActionBtnAdd(ActionEvent event) {
        // Get the new account type from the text field
        String newAccountType = txtNewAccountType.getText();

        // Check if the new account type is empty
        if (newAccountType.isEmpty()) {
            // Show error message
            //new Mensaje("Error", "Debe ingresar un tipo de cuenta para agregarlo", Mensaje.ERROR);
        } else {
            // Create a new account type
            AccountType accountType = new AccountType(newAccountType);

            // Add the new account type to the observable list
            accountTypes.add(accountType);

            // Clear the text field
            txtNewAccountType.clear();

            // Show success message
            //new Mensaje("Exito", "El tipo de cuenta ha sido agregado exitosamente", Mensaje.EXITO);
        }
    }




}




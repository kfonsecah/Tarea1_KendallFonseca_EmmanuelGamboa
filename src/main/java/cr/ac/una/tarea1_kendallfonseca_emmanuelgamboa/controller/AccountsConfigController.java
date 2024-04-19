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
    private TableView<AccountType> tableTypesAccount;

    @FXML
    private MFXButton btnAdd;

    @FXML
    private MFXButton btnDelete;


    private ObservableList<AccountType> accountTypes;


    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    @Override
    public void initialize() {


    }

    private void initializeTableView() {

    }


    @FXML
    void onActionBtnDelete(ActionEvent event) {

    }

    @FXML
    void onActionBtnSelect(ActionEvent event) {

    }

    @FXML
    void onActionBtnAdd(ActionEvent event) {

    }




}




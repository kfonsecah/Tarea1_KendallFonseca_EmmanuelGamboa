/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import io.github.palexdev.materialfx.controls.MFXButton;

public class AdminController extends Controller implements Initializable {

    @FXML
    private MFXButton btnAccountsConfig;

    @FXML
    private MFXButton btnBankConfig;

    @FXML
    private AnchorPane root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initialize() {
        // TODO
    }

    @FXML
    private void onActionBtnBankConfig(ActionEvent event) {
        FlowController.getInstance().goView("BankConfigView");
    }

    @FXML
    private void onActionBtnAccountsConfig(ActionEvent event) {
        FlowController.getInstance().goView("AccountsConfigView");
    }
}


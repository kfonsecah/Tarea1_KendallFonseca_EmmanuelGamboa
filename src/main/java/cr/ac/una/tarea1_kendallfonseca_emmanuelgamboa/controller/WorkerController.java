/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import io.github.palexdev.materialfx.controls.MFXButton;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class WorkerController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private MFXButton btnAssociateMaintenance;

    @FXML
    private MFXButton btnOpenAccounts;

    @FXML
    private MFXButton btnPrintID;

    @FXML
    private MFXButton btnWithdrawDeposits;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @Override
    public void initialize() {

    }
    private void onActionBtnAssociateMaintenance(ActionEvent event){
        //FlowController.getInstance().goView("AssociateMaintenanceView");
    }
    private void onActionBtnOpenAccounts(ActionEvent event){
        FlowController.getInstance().goView("OpenAccountsView");
    }
    private void onActionBtnPrintID(ActionEvent event){
        //FlowController.getInstance().goView("PrintIDView");
    }
    private void onActionBtnWithdrawDeposits(ActionEvent event){
        //FlowController.getInstance().goView("WithdrawDepositsView");
    }

    
}

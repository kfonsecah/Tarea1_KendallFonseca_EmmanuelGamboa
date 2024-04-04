/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import io.github.palexdev.materialfx.controls.MFXButton;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;
import javafx.scene.layout.BorderPane;

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
    private BorderPane BorderPane;

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
        printAppContextUsersInfo();

        // TODO
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onActionBtnAssociateMaintenance(ActionEvent event) {
        FlowController.getInstance().goView("AssociateMaintenanceView");
    }

    @FXML
    private void onActionBtnOpenAccounts(ActionEvent event) {
        FlowController.getInstance().goView("OpenAccountsView");
    }

    @FXML
    private void onActionBtnPrintID(ActionEvent event) {
        FlowController.getInstance().goView("CardPrintingView");
    }

    private void onActionBtnWithdrawDeposits(ActionEvent event) {
        //FlowController.getInstance().goView("WithdrawDepositsView");
    }

    private void printAppContextUsersInfo() {
        AppContext appContext = AppContext.getInstance();
        ObservableList<Associated> asociados = AppContext.getAsociados();

        for (Associated associated : asociados) {
            System.out.println("Nombre: " + associated.getAssoName());
            System.out.println("Apellido: " + associated.getAssoLastName());
            System.out.println("Edad: " + associated.getAssoAge());
            System.out.println("Folio: " + associated.getAssoFolio());
            System.out.println("Foto: " + associated.getAssoPhoto());
            System.out.println("----------------------------------");
        }
    }


}

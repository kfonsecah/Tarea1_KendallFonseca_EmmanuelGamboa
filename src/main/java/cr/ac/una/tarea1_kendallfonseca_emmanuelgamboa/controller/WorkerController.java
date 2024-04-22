/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Cooperative;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class WorkerController extends Controller implements Initializable {

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

    @FXML
    private MFXButton btnExit;


    @FXML
    private ImageView imageLogo;

    @FXML
    private Label txtCooperativeName;




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        printAppContextUsersInfo();
        setCompanyInfo();
        AppContext appContext = AppContext.getInstance();
        AppContext.readAssociatedsFromJsonFile();
        ObservableList<Associated> asociados = AppContext.getAsociados();
        System.out.println("Asociados list: " + asociados);
    }

    @Override
    public void initialize() {

    }
    private void setCompanyInfo() {
        Cooperative cooperative = (Cooperative) AppContext.getInstance().get("cooperative");

        if (cooperative != null) {
            Image logo = cooperative.getLogo();
            if (logo != null) {
                imageLogo.setImage(cooperative.getLogo());
            }

            String companyName = cooperative.getName();

            txtCooperativeName.setText(companyName);

        }
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

    @FXML
    private void onActionBtnWithdrawDeposits(ActionEvent event) {
        FlowController.getInstance().goView("WithdrawDepositsView");
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
    @FXML
    void onActionBtnExit(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

}

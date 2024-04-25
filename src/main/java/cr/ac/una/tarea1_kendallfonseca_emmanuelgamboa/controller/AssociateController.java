/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Cooperative;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class AssociateController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private MFXButton btnRegister;

    @FXML
    private MFXButton btnAccountStatements;

    @FXML
    private MFXButton btnUserDeposits;

    @FXML
    private MFXButton btnCalculator;

    @FXML
    private MFXButton btnExit;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label txtCooperativeName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCompanyInfo();
        AppContext.getInstance().setIncomeLevel("Associate");
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onActionBtnRegister(ActionEvent event) {
        FlowController.getInstance().goView("RegisterView");
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
    void onActionBtnUserDeposits(ActionEvent event) {
        FlowController.getInstance().goView("WithdrawDepositsView");

    }
    @FXML
    void onActionBtnAccountStatements(ActionEvent event) {
        FlowController.getInstance().goView("MovementsView");
    }

    @FXML
    void onActionBtnCalculator(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText("Esta pantalla solo es informativa, no crea ninguna cuenta de ahorros.");

        // Personalizar el botón de la alerta
        ButtonType buttonTypeOK = new ButtonType("Aceptar");
        alert.getButtonTypes().setAll(buttonTypeOK);

        // Mostrar la alerta y esperar a que el usuario haga clic en "Aceptar"
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOK) {
            // Si el usuario hace clic en "Aceptar", navegar a la vista "SavingsCalculatorView"
            FlowController.getInstance().goView("SavingsCalculatorView");
        }
    }
    @FXML
    void onActionBtnExit(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }


}



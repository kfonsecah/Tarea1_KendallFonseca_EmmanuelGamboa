/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Cooperative;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AdminController extends Controller implements Initializable {

    @FXML
    private MFXButton btnAccountsConfig;

    @FXML
    private MFXButton btnBankConfig;

    @FXML
    private AnchorPane root;
    
    @FXML
    private ImageView imageLogo;

    @FXML
    private Label txtCooperativeName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setCompanyInfo();
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
}


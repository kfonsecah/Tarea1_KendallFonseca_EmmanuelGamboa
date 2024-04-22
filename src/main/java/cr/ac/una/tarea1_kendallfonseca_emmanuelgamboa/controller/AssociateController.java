/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Cooperative;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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
    private MFXButton btnDeposits;

    @FXML
    private ImageView imageLogo;

    @FXML
    private Label txtCooperativeName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCompanyInfo();
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
    void onActionBtnDeposits(ActionEvent event) {
        FlowController.getInstance().goView("AssociateDepositsView");

    }


}



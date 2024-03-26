/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class RegisterController extends Controller implements Initializable {

    @FXML
    private MFXButton btnRegister;

    @FXML
    private MFXTextField txtName;

    @FXML
    private MFXButton btnPhoto;

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
    private void onActionBtnRegister(ActionEvent event) {
        // TODO: Acción al presionar el botón de registro
    }

    @FXML
    private void onActionBtnPhoto(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("WebCamView");
    }
}

    


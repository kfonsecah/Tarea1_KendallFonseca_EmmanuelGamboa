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
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class AccountsConfigController extends Controller implements Initializable {

    @FXML
    private MFXButton btnCancel;

    @FXML
    private MFXButton btnSave;

    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initialize() {
        // TODO: Implementar inicialización personalizada si es necesario
    }

    @FXML
    void onActionBtnCancel(ActionEvent event) {
        // TODO: Manejar la acción del boton Cancelar
    }

    @FXML
    void onActionBtnSave(ActionEvent event) {
        // TODO: Manejar la acción del botón Guardar
    }
}


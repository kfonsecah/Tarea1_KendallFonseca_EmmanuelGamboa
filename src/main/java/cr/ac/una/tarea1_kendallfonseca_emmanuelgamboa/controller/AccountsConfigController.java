/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class AccountsConfigController extends Controller implements Initializable {

    public class PleaseProvideControllerClassName {

        @FXML
        private MFXButton btn;

        @FXML
        private MFXButton btnAdd;

        @FXML
        private MFXButton btnEliminar;

        @FXML
        private BorderPane root;

        @FXML
        private TableView<?> tableTypesAccount;

        @FXML
        private MFXTextField txtNewAccountType;

    }


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



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import java.io.File;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class BankConfigController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private MFXButton btnChangeIcon;

    @FXML
    private MFXButton btnAccept;

    @FXML
    private MFXTextField txtBankName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initialize() {
        // TODO
    }

    public void onActionBtnChangeIcon(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Imágenes (*.png, *.jpg)", "*.png", "*.jpg");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        if (file != null && file.exists()) {
            Image image = new Image(file.toURI().toString());
            Controller.iconChanger(getStage(), image);
        }
    }

    public void onActionBtnAceptar(ActionEvent event) {
        String newName = txtBankName.getText();
        if (!newName.isEmpty()) {
            String currentName = System.getProperty("java.class.title");
            Controller.nameChanger(getStage(), newName);
        }
    }
}


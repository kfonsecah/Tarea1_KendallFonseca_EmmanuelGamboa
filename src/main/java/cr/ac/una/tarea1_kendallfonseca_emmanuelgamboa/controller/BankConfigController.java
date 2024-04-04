/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    private MFXButton btnChangeIcon;

    @FXML
    private ImageView previewLogo;

    @FXML
    private AnchorPane root;

    @FXML
    private MFXTextField txtBankName;

    @FXML
    private MFXButton btnAceptar;

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
            try {
                // Save the selected image as "newLogo.png" in the folder "cr/ac/una/tarea1_kendallfonseca_emmanuelgamboa/resources"
                Path source = file.toPath();
                Path target = Paths.get("../resources/cr/ac/una/tarea1_kendallfonseca_emmanuelgamboa/resources/newLogo.png");
                Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

                // Set the new logo as the application icon
                Controller.iconChanger(getStage(), new Image("file:../resources/cr/ac/una/tarea1_kendallfonseca_emmanuelgamboa/resources/newLogo.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
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


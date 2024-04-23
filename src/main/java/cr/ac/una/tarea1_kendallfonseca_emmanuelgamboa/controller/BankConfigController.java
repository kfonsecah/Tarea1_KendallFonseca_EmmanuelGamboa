/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.Mensaje;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
import javafx.stage.Stage;

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

    @FXML
    private void onActionBtnChangeIcon(ActionEvent event) {
        Mensaje mensaje = new Mensaje();

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Imágenes (*.png, *.jpg)", "*.png", "*.jpg");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        if (file != null && file.exists()) {
            try {
                Path source = file.toPath();
                Path target = Paths.get("src/main/resources/cr/ac/una/tarea1_kendallfonseca_emmanuelgamboa/resources/newLogo.png");
                Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

                Image image = new Image(file.toURI().toString());
                previewLogo.setImage(image);


            } catch (IOException e) {
                e.printStackTrace();
                mensaje.showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Ha ocurrido un error al guardar la imagen.");
            }
        }
    }

    @FXML
    private void onActionBtnAceptar(ActionEvent event) {
        Mensaje mensaje = new Mensaje();
        String newName = txtBankName.getText();
        if (!newName.isEmpty() && previewLogo.getImage()!=null) {
            try {
                String imagePath = "src/main/resources/cr/ac/una/tarea1_kendallfonseca_emmanuelgamboa/resources/newLogo.png";

                String txtFilePath = "cooperativa_info.txt";
                FileWriter fw = new FileWriter(txtFilePath);
                BufferedWriter writer = new BufferedWriter(fw);

                writer.write("Nombre de la cooperativa: " + newName);
                writer.newLine();
                writer.write("Ruta del logo: " + imagePath);
                writer.newLine();

                writer.close();

                Stage stage = (Stage) root.getScene().getWindow();
                stage.getIcons().clear();
                stage.getIcons().add(new Image("file:" + imagePath));

                mensaje.showModal(Alert.AlertType.INFORMATION, "Éxito", root.getScene().getWindow(), "El nombre y logo de la Cooperativa han sido cambiados, el nuevo nombre es: " + newName + ".");

            } catch (IOException e) {
                e.printStackTrace();
                mensaje.showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Ha ocurrido un error al guardar la información.");
            }
        } else if(newName.isEmpty()) {
            mensaje.showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor complete el campo de nombre.");
        }else if(previewLogo.getImage()==null){
            mensaje.showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor seleccione un logo.");
        }
    }
}


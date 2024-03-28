/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.WebCam;
import javafx.fxml.Initializable;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class WebCamController extends Controller implements Initializable {

    @FXML
    private MFXButton btnTakePhoto;

    @FXML
    private ImageView imageView;

    private WebCam webcam;

    @FXML
    private AnchorPane root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webcam = new WebCam(imageView);
        webcam.start();
        webcam.updateImageView();
        // TODO
    }

    @Override
    public void initialize() {

        // TODO
    }

    @FXML
    private void onBtnActivateCamera(ActionEvent event) {

    }

    @FXML
    private void onActionBtnTakePhoto(ActionEvent event) {
        webcam.takePhoto();
    }

    @FXML
    private void onActionBtnStopCam(ActionEvent event) {
        webcam.stop();
    }
}


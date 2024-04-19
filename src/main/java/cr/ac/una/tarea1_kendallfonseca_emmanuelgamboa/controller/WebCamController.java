/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.Mensaje;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.WebCam;
import javafx.fxml.Initializable;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private MFXButton btnSavePhoto;

    @FXML
    private ImageView imageView;

    @FXML
    private MFXButton btnRetakePhoto;

    @FXML
    private MFXButton tempImageView;

    private WebCam webcam;

    @FXML
    private AnchorPane root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void initialize() {
        btnRetakePhoto.setVisible(false);
        btnSavePhoto.setVisible(false);
        btnTakePhoto.setVisible(true);

        webcam = new WebCam(imageView);
        webcam.start();
        webcam.updateImageView();
    }

    @FXML
    private void onBtnActivateCamera(ActionEvent event) {

    }

    @FXML
    private void onActionBtnTakePhoto(ActionEvent event) {
        webcam.takePhoto();
        btnTakePhoto.setVisible(false);
        btnRetakePhoto.setVisible(true);
        btnSavePhoto.setVisible(true);
    }

    @FXML
    private void onActionBtnRetakePhoto(ActionEvent event) {
        webcam.retakePhoto();
        btnTakePhoto.setVisible(true);
        btnRetakePhoto.setVisible(false);
        btnSavePhoto.setVisible(false);

    }


    @FXML
    private void onActionBtnSavePhoto(ActionEvent event) {
        webcam.savePhoto();
        webcam.stop();
        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Foto guardada", root.getScene().getWindow(), "La foto ha sido guardada con éxito");
        getStage().close();

    }



    @FXML
    private void onActionBtnStopCam(ActionEvent event) {
    }
}


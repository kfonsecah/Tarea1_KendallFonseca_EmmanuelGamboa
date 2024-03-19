/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;


import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.WebCam;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import com.github.sarxos.webcam.Webcam;
import javafx.scene.image.ImageView;
import io.github.palexdev.materialfx.controls.MFXButton;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;


/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class WebCamController extends Controller implements Initializable {

    private WebCam WebCam;

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView webcamImageView;
    
    @FXML
    private MFXButton btnActivate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    @Override
    public void initialize() {

    }

    @FXML
    private void onActionBtnActivate(ActionEvent event) {
        try {
            WebCam = new WebCam(webcamImageView);
            WebCam.start();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
       
    }


}

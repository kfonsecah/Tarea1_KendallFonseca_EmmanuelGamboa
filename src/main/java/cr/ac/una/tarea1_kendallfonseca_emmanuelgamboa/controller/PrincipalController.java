/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import io.github.palexdev.materialfx.controls.MFXButton;


public class PrincipalController extends Controller implements Initializable {
    @FXML
    private MFXButton btnAdmin;

    @FXML
    private MFXButton btnWorker;

    @FXML
    private BorderPane root;

    @FXML
    private MFXButton btnAssociate;

    @FXML
    private ImageView imvFondo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }
    @Override
    public void initialize() {

    }
    @FXML
    private void onActionBtnAdmin(ActionEvent event){
        //System.out.println("Admin");
        FlowController.getInstance().goView("AdminView");
    }
    @FXML
    private void onActionBtnWorker(ActionEvent event){
        //System.out.println("Worker");
        FlowController.getInstance().goView("WorkerView");
    }

    @FXML
    private void onActionBtnAssociate(ActionEvent event){
        //System.out.println("Associate");
        FlowController.getInstance().goView("AssociateView");
    }
    
}

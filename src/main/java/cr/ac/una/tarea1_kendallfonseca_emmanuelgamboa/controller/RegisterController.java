/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.sun.source.tree.WhileLoopTree;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;
import javafx.event.ActionEvent;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.Mensaje;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;

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
    private MFXTextField txtLastName;

    @FXML
    private MFXTextField txtAge;

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
        try{
            if(txtName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtAge.getText().isEmpty()){
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor complete todos los campos");
            }else{
                Associated associated = new Associated(txtName.getText(), txtLastName.getText(), Integer.parseInt(txtAge.getText()), "");
                AppContext.getInstance().set(associated.getAssoFolio(), associated);
                   new Mensaje().showModal(Alert.AlertType.INFORMATION, "Registro", root.getScene().getWindow(), "Registro exitoso, Su numero de asociado es:"+associated.getAssoFolio());
            }
        }catch(Exception e){
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Error al registrar");
        }
    }

    @FXML
    private void onActionBtnPhoto(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("WebCamView");
        //QUE SE SOLICITE DIGITAR EL FOLIO

        //JUST TEST
        while(true){
            System.out.println("Folio: ");
            Scanner scanner = new Scanner(System.in);
            String folio = scanner.nextLine();
            Associated associated = (Associated) AppContext.getInstance().get(folio);
            System.out.println(associated.getAssoName());
            System.out.println(associated.getAssoLastName());
            System.out.println(associated.getAssoAge());
        }

    }
}

    


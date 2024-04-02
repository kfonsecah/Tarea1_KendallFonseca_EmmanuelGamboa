/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.collections.ObservableList;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class AssociateMaintenanceController extends Controller implements Initializable {


    @FXML
    private TableView<Associated> UsersData;

    @FXML
    private TableColumn<Associated, String> columnNameUser;
    @FXML
    private TableColumn<Associated, String> columnLastNameUser;
    @FXML
    private TableColumn<Associated, Integer> columnAgeUser;
    @FXML
    private TableColumn<Associated, String> columnFolioUser;
    @FXML
    private TableColumn<Associated, ImageView> columnPhotoUser;

    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getUsersData();
    }
    @Override
    public void initialize() {
        // TODO
    }
    //method to get the users data and added to the table view
    //get the data the app context HashMap
//    private void getUsersData(){
//        UsersData.getItems().addAll();
//    }
    private void getUsersData(){
        ObservableList<Associated> usuarios = (ObservableList<Associated>) AppContext.getAssociated();

        // Recorrer la lista de usuarios y mostrar sus datos en la consola
        for (Associated usuario : usuarios) {
            System.out.println("Nombre: " + usuario.getAssoName());
            System.out.println("Apellido: " + usuario.getAssoLastName());
            System.out.println("Edad: " + usuario.getAssoAge());
            System.out.println("Folio: " + usuario.getAssoFolio());
            // Considerando que la imagen es un String (URL) en este ejemplo
            System.out.println("Foto: " + usuario.getAssoPhoto());
            System.out.println("--------------------------------");
        }
    }
    
}

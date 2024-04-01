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
    private void getUsersData(){
        UsersData.getItems().addAll();

    }

    
}

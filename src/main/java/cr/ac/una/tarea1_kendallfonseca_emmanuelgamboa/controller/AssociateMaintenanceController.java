/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        readUsers();

    }
    @Override
    public void initialize() {
        // TODO
    }

    public void readUsers() {
        String filePath = "Asociados.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[") && line.endsWith("]")) {
                    line = line.replaceAll("[\\[\\]]", "");
                    String[] userInfo = line.split(",");
                    if (userInfo.length == 5) {
                        System.out.println("Name: " + userInfo[0]);
                        System.out.println("Last Name: " + userInfo[1]);
                        System.out.println("Age: " + userInfo[2]);
                        System.out.println("Folio: " + userInfo[3]);
                        System.out.println("User File Path: " + userInfo[4]);
                        System.out.println("----------------------------------");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from the file: " + e.getMessage());
        }
    }
}
    


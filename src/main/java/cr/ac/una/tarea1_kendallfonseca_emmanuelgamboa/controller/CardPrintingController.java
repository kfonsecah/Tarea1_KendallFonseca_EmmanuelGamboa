/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class CardPrintingController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private MFXButton btnPrint;

    @FXML
    private ImageView imageID;

    @FXML
    private ImageView imagePreview;

    @FXML
    private AnchorPane root;

    @FXML
    private Label txtAge;

    @FXML
    private Label txtFolio;

    @FXML
    private Label txtName;


    @FXML
    private TableView<Associated> usersTable;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppContext appContext = AppContext.getInstance();
        ObservableList<Associated> asociados = AppContext.getAsociados();
        loadUsersToTableView();
        // populateTextFieldValues();

    }
    @Override
    public void initialize(){
    }
    private void loadUsersToTableView() {
        // Create columns
        TableColumn<Associated, String> folioColumn = new TableColumn<>("Folio");
        TableColumn<Associated, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Associated, String> lastNameColumn = new TableColumn<>("Last Name");
        TableColumn<Associated, Integer> ageColumn = new TableColumn<>("Age");

        // Set cell value factories
        folioColumn.setCellValueFactory(new PropertyValueFactory<>("assoFolio"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("assoName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("assoLastName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("assoAge"));

        // Set columns
        usersTable.getColumns().addAll(folioColumn, nameColumn, lastNameColumn, ageColumn);

        // Load data
        ObservableList<Associated> asociados = AppContext.getAsociados();
        usersTable.setItems(asociados);

        usersTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            populateTextFieldValues();
        });
    }

    private void populateTextFieldValues() {
        Associated selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            txtName.setText(selectedUser.getAssoName());
            //txtLastName.setText(selectedUser.getAssoLastName());
            txtFolio.setText(selectedUser.getAssoFolio());
            txtAge.setText(String.valueOf(selectedUser.getAssoAge()));


            String imageFileName = selectedUser.getAssoFolio() + ".png";
            String imagePath = "userphotos/" + imageFileName;
            File imageFile = new File(imagePath);

            if (imageFile.exists()) {
                Image image = new Image(imageFile.toURI().toString());
                imagePreview.setImage(image);
            } else {

            }
        }
    }

}

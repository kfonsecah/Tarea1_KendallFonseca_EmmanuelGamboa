/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Associated;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.Mensaje;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;


/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class AssociateMaintenanceController extends Controller implements Initializable {

    @FXML
    private MFXButton btnEditImage;

    @FXML
    private MFXButton btnAccept;

    @FXML
    private MFXButton btnNewPhoto;

    @FXML
    private AnchorPane root;

    @FXML
    private MFXTextField txtAge;

    @FXML
    private Label txtFolio;

    @FXML
    private MFXTextField txtLastName;

    @FXML
    private MFXTextField txtName;

    @FXML
    private ImageView userImage;

    @FXML
    private TableView<Associated> userSearchList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppContext appContext = AppContext.getInstance();
        ObservableList<Associated> asociados = AppContext.getAsociados();
        loadUsersToTableView();
        populateTextFieldValues();

    }

    @Override
    public void initialize() {
        // TODO
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
        userSearchList.getColumns().addAll(folioColumn, nameColumn, lastNameColumn, ageColumn);

        // Load data
        ObservableList<Associated> asociados = AppContext.getAsociados();
        userSearchList.setItems(asociados);

        userSearchList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            populateTextFieldValues();
        });
    }

    private void populateTextFieldValues() {
        Associated selectedUser = userSearchList.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            txtName.setText(selectedUser.getAssoName());
            txtLastName.setText(selectedUser.getAssoLastName());
            txtFolio.setText(selectedUser.getAssoFolio());
            txtAge.setText(String.valueOf(selectedUser.getAssoAge()));


            String imageFileName = selectedUser.getAssoFolio() + ".png";
            String imagePath = "userphotos/" + imageFileName;
            File imageFile = new File(imagePath);

            if (imageFile.exists()) {
                Image image = new Image(imageFile.toURI().toString());
                userImage.setImage(image);
            } else {

            }
        }
    }

    @FXML
    private void onActionBtnEditImage() {
        if (userSearchList.getSelectionModel().getSelectedItem() == null) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Seleccione un usuario");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                userImage.setImage(image);

                Associated selectedUser = userSearchList.getSelectionModel().getSelectedItem();
                String newImagePath = selectedFile.getAbsolutePath();
                selectedUser.setAssoPhoto(newImagePath);

                String imageFileName = selectedUser.getAssoFolio() + ".png";
                String destinationPath = "userphotos/" + imageFileName;
                Path destination = new File(destinationPath).toPath();
                Files.copy(Path.of(newImagePath), destination, StandardCopyOption.REPLACE_EXISTING);

                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Exito", root.getScene().getWindow(), "Imagen actualizada correctamente");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onActionNewPhoto() {
    }

    @FXML
    private void onActionBtnAccept() {
        Associated selectedUser = userSearchList.getSelectionModel().getSelectedItem();

        if (selectedUser == null) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Seleccione un usuario");
        } else {
            String newName = txtName.getText();
            String newLastName = txtLastName.getText();
            int newAge = Integer.parseInt(txtAge.getText());

            if (newName.isEmpty() || newLastName.isEmpty() || newAge <= 0) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor complete todos los campos");
                return;
            }

            selectedUser.setAssoName(newName);
            selectedUser.setAssoLastName(newLastName);
            selectedUser.setAssoAge(newAge);

            String newImagePath = selectedUser.getAssoPhoto();
            if (newImagePath != null && !newImagePath.isEmpty()) {
                String imageFileName = selectedUser.getAssoFolio() + ".png";
                String destinationPath = "userphotos/" + imageFileName;
                Path destination = new File(destinationPath).toPath();

                Associated.AssociatedData associatedData = new Associated.AssociatedData(
                        selectedUser.getAssoName(),
                        selectedUser.getAssoLastName(),
                        selectedUser.getAssoAge(),
                        selectedUser.getAssoFolio(),
                        selectedUser.getAssoPhoto(),
                        selectedUser.getIban()
                );

                try {
                    AppContext.addAssociatedToJsonFile(associatedData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                loadUsersToTableView();
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Exito", root.getScene().getWindow(), "Usuario actualizado correctamente");

                txtName.setText("");
                txtLastName.setText("");
                txtAge.setText("");
                userImage.setImage(null);
                txtFolio.setText("");

            }
        }
    }
}



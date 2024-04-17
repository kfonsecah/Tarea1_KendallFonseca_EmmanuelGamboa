/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import javafx.collections.FXCollections;
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

    @FXML
    private MFXTextField txtSearch;

    @FXML
    private MFXButton btnSearch;

    @FXML
    private MFXComboBox<String> comboBoxFilter;

    @FXML
    private MFXButton btnDeleteUser;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppContext appContext = AppContext.getInstance();
        ObservableList<Associated> asociados = AppContext.getAsociados();
        loadUsersToTableView();
        populateTextFieldValues();
        comboBoxFilter.getItems().addAll("Todo", "Nombre", "Apellido", "Folio", "Edad");
        ChangeListener<ObservableList<Associated>> tableItemsListener = new ChangeListener<ObservableList<Associated>>() {
            @Override
            public void changed(ObservableValue<? extends ObservableList<Associated>> observable, ObservableList<Associated> oldValue, ObservableList<Associated> newValue) {
                // Set the value of the combo box to "Todo" after the table view has been populated with data
                comboBoxFilter.setValue("Todo");
                // Remove the listener after it has been triggered once
                userSearchList.itemsProperty().removeListener(this);
            }
        };
    }

    @Override
    public void initialize() {
        // TODO
    }

    private void loadUsersToTableView() {
        // Create columns
        TableColumn<Associated, String> folioColumn = new TableColumn<>("Folio");
        TableColumn<Associated, String> nameColumn = new TableColumn<>("Nombre");
        TableColumn<Associated, String> lastNameColumn = new TableColumn<>("Apellido");
        TableColumn<Associated, Integer> ageColumn = new TableColumn<>("Edad");


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

    @FXML
    private void onActionBtnSearch(ActionEvent event) {
        String filter = comboBoxFilter.getValue();
        if (!txtSearch.getText().isEmpty()) {
            ObservableList<Associated> filteredData = FXCollections.observableArrayList();
            ObservableList<Associated> list = AppContext.getAsociados();

            for (int i = 0; i < list.size(); i++) {
                Associated data = list.get(i);
                if(filter.equals("Todo")&& data.getAssoName().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                        data.getAssoLastName().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                        data.getAssoFolio().toLowerCase().contains(txtSearch.getText().toLowerCase()) ||
                        Integer.toString(data.getAssoAge()).contains(txtSearch.getText())){
                    filteredData.add(data);
                } else if (filter.equals("Nombre") && data.getAssoName().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                    filteredData.add(data);
                } else if (filter.equals("Apellido") && data.getAssoLastName().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                    filteredData.add(data);
                } else if (filter.equals("Folio") && data.getAssoFolio().toLowerCase().contains(txtSearch.getText().toLowerCase())) {
                    filteredData.add(data);
                } else if (filter.equals("Edad") && Integer.toString(data.getAssoAge()).contains(txtSearch.getText())) {
                    filteredData.add(data);
                }
            }
            userSearchList.setItems(filteredData);
        } else {
            cleanUserTableView();
            loadUsersToTableView();
        }
    }

    private void cleanUserTableView() {
        userSearchList.getItems().clear();
        userSearchList.getColumns().clear();
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
    private void onActionBtnEditImage(ActionEvent event) {
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
    private void onActionBtnAccept(ActionEvent event) {
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
            if (newImagePath!= null &&!newImagePath.isEmpty()) {
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

                // Update the TableView with the edited Associated object
                updateTableView();

                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Exito", root.getScene().getWindow(), "Usuario actualizado correctamente");

                txtName.setText("");
                txtLastName.setText("");
                txtAge.setText("");
                userImage.setImage(null);
                txtFolio.setText("");
            }
        }
    }

    private void updateTableView() {
        // Get the updated Associated objects from the AppContext
        ObservableList<Associated> asociados = AppContext.getAsociados();

        // Update the TableView with the updated Associated objects
        int selectedIndex = userSearchList.getSelectionModel().getSelectedIndex();
        userSearchList.setItems(asociados);
        userSearchList.getSelectionModel().select(selectedIndex);
        userSearchList.refresh(); // Refresh the TableView to display the updated Associated object
    }


    private void deleteAssociated() {
        int selectedIndex = userSearchList.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("No user selected.");
            alert.showAndWait();
            return;
        }

        String folio = txtFolio.getText();
        if (folio.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Folio cannot be empty.");
            alert.showAndWait();
            return;
        }

        Associated associatedData = userSearchList.getItems().get(selectedIndex);
        associatedData.setFolio(folio);

        AppContext appContext = AppContext.getInstance();

        String result = appContext.deleteAssociatedFromJsonFile(associatedData);

        userSearchList.getItems().remove(selectedIndex);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Result");
        alert.setContentText(result);
        alert.showAndWait();
    }
    @FXML
    private void onActionDeleteUser(ActionEvent event) {
        deleteAssociated();
        updateTableView();
    }
}



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AccountUser;
import javafx.event.ActionEvent;
import java.awt.image.BufferedImage;
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
    private MFXButton addAssociate;

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



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Deshabilitar los botones de editar y eliminar al inicio
        btnEditImage.setDisable(true);
        btnDeleteUser.setDisable(true);
        btnAccept.setDisable(true);
        btnNewPhoto.setDisable(false);
        addAssociate.setDisable(true);
        btnNewPhoto.setDisable(true);

        // Configurar la TableView y otros elementos
        configureTableView();
        comboBoxFilter.getItems().addAll("Todo", "Nombre", "Apellido", "Folio", "Edad");
        comboBoxFilter.setValue("Todo");

        // Configurar el listener para la selección de elementos en la TableView
        userSearchList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Habilitar los botones de editar y eliminar si se selecciona un usuario
            btnEditImage.setDisable(newValue == null);
            btnDeleteUser.setDisable(newValue == null);
            btnAccept.setDisable(newValue == null);
            btnNewPhoto.setDisable(newValue == null);
            addAssociate.setDisable(true);
        });
    }

    private void configureTableView() {
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
        loadUsersToTableView();
    }

    private void loadUsersToTableView() {
        ObservableList<Associated> asociados = AppContext.getAsociados();
        userSearchList.setItems(asociados);

        userSearchList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            populateTextFieldValues();
        });
    }


    @Override
    public void initialize() {
        // TODO
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
            updateTableView();

        }
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
        FlowController.getInstance().goViewInWindow("WebCamView");
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

        ObservableList<Associated> asociados = AppContext.getAsociados();

        // Update the TableView with the updated Associated objects
        int selectedIndex = userSearchList.getSelectionModel().getSelectedIndex();
        userSearchList.setItems(asociados);
        userSearchList.getSelectionModel().select(selectedIndex);
        userSearchList.refresh();
    }

    private void deleteAssociated() {
        int selectedIndex = userSearchList.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("No se ha seleccionado ningún usuario.");
            alert.showAndWait();
            return;
        }

        Associated associatedData = userSearchList.getItems().get(selectedIndex);
        String folio = associatedData.getAssoFolio();

        AppContext appContext = AppContext.getInstance();
        AccountUser accountUser = new AccountUser();

        // Obtener las cuentas asociadas al usuario que se desea eliminar
        ObservableList<Account> userAccounts = accountUser.getAccountsByFolio(folio);

        // Verificar si alguna cuenta asociada tiene un saldo mayor que cero
        for (Account account : userAccounts) {
            if (account.getBalance() > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("No se puede eliminar el usuario porque tiene una cuenta con saldo mayor que cero.");
                alert.showAndWait();
                return;
            }
        }

        // Eliminar todas las cuentas asociadas al usuario del archivo JSON
        for (Account account : userAccounts) {
            accountUser.getAccountsObservableList().remove(account);
            // Aquí es donde se elimina la cuenta del archivo JSON
            try {
                accountUser.removeAccountFromJsonFile(account);
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar el error de alguna manera
            }
        }

        // Eliminar al usuario
        associatedData.setFolio(txtFolio.getText());
        String result = appContext.deleteAssociatedFromJsonFile(associatedData);
        userSearchList.getItems().remove(selectedIndex);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Resultado");
        alert.setContentText(result);
        alert.showAndWait();
    }




    @FXML
    private void onActionDeleteAssociate(ActionEvent event) {
        deleteAssociated();
        updateTableView();

        txtName.setText("");
        txtLastName.setText("");
        txtAge.setText("");
        userImage.setImage(null);
        txtFolio.setText("");
        userSearchList.getSelectionModel().clearSelection();

    }

    @FXML
    void onActionAdd(ActionEvent event) {
        try {
            if (txtName.getText().isEmpty()  || txtLastName.getText().isEmpty() || txtAge.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor complete todos los campos");
            } else if (userImage.getImage() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor tome su fotografia");
            } else  {

                Associated associated = new Associated(txtName.getText(), txtLastName.getText(), Integer.parseInt(txtAge.getText()), "", "", "");

                associated.createIban();
                associated.setIban(associated.getIban());

                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Registro", root.getScene().getWindow(), "Registro exitoso, Su numero de asociado es:" + associated.createFolio());

                Associated.AssociatedData associatedData = new Associated.AssociatedData(
                        associated.getAssoName(),
                        associated.getAssoLastName(),
                        associated.getAssoAge(),
                        associated.getAssoFolio(),
                        associated.getAssoPhoto(),
                        associated.getIban()
                );


                AppContext.getAsociados().add(associated);
                renameLastUserPhoto(associated.getAssoFolio());
                updateTableView();
                userSearchList.refresh();

                try {
                    Associated.addAssociatedToJsonFile(associatedData);
                    // Limpiar los campos de texto y la imagen
                    txtName.setText("");
                    txtLastName.setText("");
                    txtAge.setText("");
                    userImage.setImage(null);
                    txtFolio.setText("");
                    userSearchList.getSelectionModel().clearSelection();

                    loadUsersToTableView();
                } catch (IOException e) {
                    e.printStackTrace();
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Error al agregar usuario");
                }
            }

        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }


    private void renameLastUserPhoto(String folio) {
        File photoFile = new File("userphotos/photo1.png");
        if (photoFile.exists()) {
            String filePath = photoFile.getParent();
            String newFileName = folio + ".png";
            File newFile = new File(filePath, newFileName);

            if (photoFile.renameTo(newFile)) {
                System.out.println("La foto 'photo1.png' se ha renombrado correctamente a: " + newFileName);
                loadLastUserPhoto();
            } else {
                System.out.println("No se pudo renombrar la foto 'photo1.png'.");
            }
        } else {

        }
    }
    @FXML
    private void loadLastUserPhoto() {
        File photoFile = new File("userphotos/photo1.png");

        if (photoFile.exists()) {
            Image image = new Image(photoFile.toURI().toString());
            userImage.setImage(image);
        } else {
            System.out.println("No se pudo cargar la foto 'photo1.png'.");
        }
    }
    @FXML
    private void onNewUser(ActionEvent event) {
        txtName.setText("");
        txtLastName.setText("");
        txtAge.setText("");
        userImage.setImage(null);
        txtFolio.setText("");
        userSearchList.getSelectionModel().clearSelection();
        btnAccept.setDisable(true);
        btnEditImage.setDisable(true);
        btnDeleteUser.setDisable(true);
        btnNewPhoto.setDisable(false);
        addAssociate.setDisable(false);
    }


}





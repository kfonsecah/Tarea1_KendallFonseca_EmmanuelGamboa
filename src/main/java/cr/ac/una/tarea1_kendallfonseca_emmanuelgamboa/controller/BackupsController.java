/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.net.URL;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class BackupsController extends Controller implements Initializable {

    @FXML
    private MFXButton loadBackup;

    @FXML
    private MFXButton madeBackup;

    @FXML
    private AnchorPane root;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initialize() {

    }

    @FXML
    void onActionBtnMadeBackup(ActionEvent event) {
        try {
            Path backupDirPath = Paths.get("backups");
            if (!Files.exists(backupDirPath)) {
                Files.createDirectory(backupDirPath);
            }

            String[] filesToBackup = {"account_types.json", "accounts.json", "associateds.json", "cooperativa_info.txt", "deposits.json"};

            for (String fileName : filesToBackup) {
                Path sourceFilePath = Paths.get(fileName);
                Path targetFilePath = backupDirPath.resolve(fileName);
                Files.copy(sourceFilePath, targetFilePath, StandardCopyOption.REPLACE_EXISTING);
            }

            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Éxito", root.getScene().getWindow(), "Copia de seguridad realizada correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Error al realizar la copia de seguridad.");
        }
    }

    @FXML
    void onActionLoadBackup(ActionEvent event) {
        try {
            Path backupDirPath = Paths.get("backups");
            if (!Files.exists(backupDirPath)) {
                // Si no existe, mostrar mensaje indicando que no hay respaldos creados
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Información", root.getScene().getWindow(), "No hay respaldos creados.");
                return;
            }

            String[] filesToRestore = {"account_types.json", "accounts.json", "associateds.json", "cooperativa_info.txt", "deposits.json"};

            for (String fileName : filesToRestore) {
                Path sourceFilePath = backupDirPath.resolve(fileName);
                Path targetFilePath = Paths.get(fileName);
                Files.copy(sourceFilePath, targetFilePath, StandardCopyOption.REPLACE_EXISTING);
            }

            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Éxito", root.getScene().getWindow(), "Archivos restaurados correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Error al restaurar los archivos.");
        }
    }

}
    


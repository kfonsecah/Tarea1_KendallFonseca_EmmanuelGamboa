/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.net.URL;
import java.util.Comparator;
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
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;

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

    @FXML
    private ImageView userPhotoPrev;

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
        try {
            if (txtName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtAge.getText().isEmpty()){
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor complete todos los campos");
            }
            else if(userPhotoPrev.getImage() == null){
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor tome su fotografia");
            }
            else{
                Associated associated = new Associated(txtName.getText(), txtLastName.getText(), Integer.parseInt(txtAge.getText()), userPhotoPrev.getImage().getUrl());

                //AppContext.getInstance().set(associated.getAssoFolio(), associated);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Registro", root.getScene().getWindow(), "Registro exitoso, Su numero de asociado es:" + associated.getAssoFolio());
                renameLastUserPhoto(associated.getAssoFolio());
                associated.Associated.add(Associated.getAssoName());
                associated.Associated.add(String.valueOf(associated.getAssoAge()));
                associated.Associated.add(associated.createFolio());
                associated.Associated.add(associated.getAssoPhoto());
                associated.createFile(associated);

                txtName.setText("");
                txtLastName.setText("");
                txtAge.setText("");
                userPhotoPrev.setImage(null);
                //System.out.println(associated.getAssoPhoto());

            }
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Error al registrar");
        }
    }

    @FXML
    private void onActionBtnPhoto(ActionEvent event) {
        if (userPhotoPrev.getImage() == null) {
            FlowController.getInstance().goViewInWindow("WebCamView");
        }else{
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Su fotografia ya ha sido tomada");
        }

    }





        //QUE SE SOLICITE DIGITAR EL FOLIO

        //JUST TEST
//        while(true){
//            System.out.println("Folio: ");
//            Scanner scanner = new Scanner(System.in);
//            String folio = scanner.nextLine();
//            Associated associated = (Associated) AppContext.getInstance().get(folio);
//            System.out.println(associated.getAssoName());
//            System.out.println(associated.getAssoLastName());
//            System.out.println(associated.getAssoAge());
//        }


    @FXML
    private void loadLastUserPhoto() {
        File photoFile = new File("userphotos/photo1.png");

        if (photoFile.exists()) {
            Image image = new Image(photoFile.toURI().toString());
            userPhotoPrev.setImage(image);
        } else {
            // No se encontró la imagen photo1.png
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
                loadLastUserPhoto(); // Cargar la nueva imagen renombrada
            } else {
                System.out.println("No se pudo renombrar la foto 'photo1.png'.");
            }
        } else {

        }
    }

    //metodo tan complejo
//    private void renameLastUserPhoto(String folio) {
//        File folder = new File("userphotos/");
//        File[] files = folder.listFiles();
//        if (files != null && files.length > 0) {
//            Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
//            File lastPhotoFile = files[0];
//            String filePath = lastPhotoFile.getParent();
//            String newFileName = folio + ".png";
//            File newFile = new File(filePath, newFileName);
//            lastPhotoFile.renameTo(newFile);
//            System.out.println("La ultima foto tomada se ha renombrado correctamente a: " + newFile.getName());
//            loadLastUserPhoto();
//
//        } else {
//            // No hay imagenes en la carpeta userphotos
//            // imagen predeterminada o mostrar un mensaje al usuario
//        }
//    }
}





    


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.sun.source.tree.WhileLoopTree;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
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


/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class RegisterController extends Controller implements Initializable {

    @FXML
    private MFXButton btnPhoto;

    @FXML
    private MFXButton btnRegister;

    @FXML
    private AnchorPane root;

    @FXML
    private MFXTextField txtAge;

    @FXML
    private MFXTextField txtLastName;

    @FXML
    private MFXTextField txtName;

    @FXML
    private ImageView userPhotoPrev;

    private ArrayList<Associated> associateds = new ArrayList<>();

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
                Associated associated = new Associated(txtName.getText(), txtLastName.getText(), Integer.parseInt(txtAge.getText()), "","", "");

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

                renameLastUserPhoto(associated.getAssoFolio());

                try {
                    associated.addAssociatedToJsonFile(associatedData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                txtName.setText("");
                txtLastName.setText("");
                txtAge.setText("");
                userPhotoPrev.setImage(null);

                Account account = new Account(associated.getIban(), "Cuenta de usuario", 0, "CRC", associated.getAssoFolio());
                writeAccountToFile(account);





                for (int i = 0; i < associated.Associated.size(); i++) {
                    System.out.println(associated.Associated.get(i));
                }

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
                loadLastUserPhoto();
            } else {
                System.out.println("No se pudo renombrar la foto 'photo1.png'.");
            }
        } else {

        }
    }

    private void writeAccountToFile(Account account) {
        File file = new File("inactiveAccounts.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (!file.exists()) {
                file.createNewFile();
            }

            String accountInfo = String.format("[%s/%s/%.2f/%s/%s]%n",
                    account.getAccountNumber(),
                    account.getAccountType(),
                    account.getBalance(),
                    account.getCurrency(),
                    account.getAccountHolder());

            writer.write(accountInfo);
        } catch (IOException e) {
            e.printStackTrace();
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Error al escribir la cuenta en el archivo");
        }
    }
    private void findAndMoveAccount(Account account, ListView<Account> fromListView, ListView<Account> toListView) {
        if (fromListView.getItems().contains(account)) {
            fromListView.getItems().remove(account);
            toListView.getItems().add(account);
        } else {
            System.out.println("Error: The account was not found in either the active or inactive accounts list.");
        }
    }

}





    


package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.WebCam;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WebCamController extends Controller implements Initializable {

    @FXML
    private MFXButton btnTakePhoto;

    @FXML
    private ImageView imagePrev;

    @FXML
    private MFXButton btnSavePhoto;

    @FXML
    private ImageView imageView;

    @FXML
    private MFXButton btnRetakePhoto;

    @FXML
    private MFXButton tempImageView;

    private WebCam webcam;

    @FXML
    private AnchorPane root;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void initialize() {


        btnRetakePhoto.setVisible(false);
        btnSavePhoto.setVisible(false);
        btnTakePhoto.setVisible(true);

        webcam = new WebCam(imageView);
        webcam.start();
        webcam.updateImageView();

        // Limitar la resolución de la imagen en la vista imageView

    }

    @FXML
    private void onBtnActivateCamera(ActionEvent event) {

    }
    // Método para tomar una foto
    @FXML
    private void onActionBtnTakePhoto(ActionEvent event) {
        // Tomar la foto antes de actualizar la vista previa
        webcam.takePhoto();
        // Actualizar la vista previa con la última imagen capturada
        imagePrev.setImage(webcam.getCapturedImage());

        btnTakePhoto.setVisible(false);
        btnRetakePhoto.setVisible(true);
        btnSavePhoto.setVisible(true);
    }


    @FXML
    private void onActionBtnRetakePhoto(ActionEvent event) {
        webcam.retakePhoto();
        btnTakePhoto.setVisible(true);
        btnRetakePhoto.setVisible(false);
        btnSavePhoto.setVisible(false);
        // Limpiar la vista previa de la foto
        imagePrev.setImage(null);
    }

    @FXML
    private void onActionBtnSavePhoto(ActionEvent event) {
        // Obtener la imagen de la vista previa
        Image imageToSave = imagePrev.getImage();

        if (imageToSave != null) {
            File file = null;
            try {
                String filePath = "userphotos/";
                File folder = new File(filePath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                int count = 1;
                String fileName = "photo" + count + ".png";
                file = new File(folder, fileName);
                while (file.exists()) {
                    count++;
                    fileName = "photo" + count + ".png";
                    file = new File(folder, fileName);
                }

                // Guardar la imagen de la vista previa
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageToSave, null);
                ImageIO.write(bufferedImage, "PNG", file);
                System.out.println("Photo saved successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to save photo!");

                // Eliminar el archivo si ocurre un error durante el guardado
                if (file != null && file.exists()) {
                    file.delete();
                    System.out.println("Photo file deleted due to error!");
                }
            }
        } else {
            System.out.println("No photo taken to save!");
        }

        // Detener la cámara y cerrar la ventana
        webcam.stop();
        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Foto guardada", root.getScene().getWindow(), "La foto ha sido guardada con éxito");
        getStage().close();
    }


    @FXML
    private void onActionBtnStopCam(ActionEvent event) {
    }
}

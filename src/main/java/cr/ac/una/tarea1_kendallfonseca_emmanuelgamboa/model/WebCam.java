package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import com.github.sarxos.webcam.Webcam;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class WebCam {

    private final Webcam webcam;
    private BufferedImage lastImage;
    private final ImageView imageView;
    private Image capturedImage;
    private boolean photoTaken = false;
    private Consumer<String> onImageCapturedListener;


    public void setOnImageCapturedListener(Consumer<String> listener) {
        this.onImageCapturedListener = listener;
    }

    public WebCam(ImageView imageView) {
        webcam = Webcam.getDefault();
        this.imageView = imageView;
    }

    public void start() {
        webcam.getLock().disable();
        webcam.open();
        new Thread(() -> {
            while (true) {
                try {
                    lastImage = webcam.getImage();
                    if (!photoTaken) {
                        updateImageView();
                    }
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }

    public void updateImageView() {
        if (lastImage != null) {
            Platform.runLater(() -> imageView.setImage(SwingFXUtils.toFXImage(lastImage, null)));
        }
    }

    public void takePhoto() {
        if (lastImage != null) {
            capturedImage = SwingFXUtils.toFXImage(lastImage, null);
            photoTaken = true;
            lastImage = null; // Limpiar lastImage después de tomar la foto
        }
    }

    public void retakePhoto() {
        photoTaken = false;
    }


    public Image getCapturedImage() {
        return capturedImage;
    }


    public void stop() {
        webcam.close();
    }
}


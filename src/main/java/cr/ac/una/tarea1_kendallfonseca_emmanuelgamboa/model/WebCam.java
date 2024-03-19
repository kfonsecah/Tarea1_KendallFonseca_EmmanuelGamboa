package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;



import com.github.sarxos.webcam.Webcam;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author kfonsecah
 */
public class WebCam{

    private Webcam webcam;
    private BufferedImage lastImage;
    private ImageView imageView;

    public WebCam(ImageView imageView) {
        webcam = Webcam.getDefault();
        this.imageView = imageView;
    }

    public void start() {
        webcam.open();
        new Thread(() -> {
            while (true) {
                lastImage = webcam.getImage();
                updateImageView();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void updateImageView() {
        if (lastImage != null) {
            SwingFXUtils.toFXImage(lastImage, null);
            Platform.runLater(() -> imageView.setImage(SwingFXUtils.toFXImage(lastImage, null)));
        }
    }
}

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

public class WebCam {

    private final Webcam webcam;
    private BufferedImage lastImage;
    private final ImageView imageView;

    public WebCam(ImageView imageView) {
        webcam = Webcam.getDefault();
        this.imageView = imageView;
    }

    public void start() {
        webcam.getLock().disable();
        webcam.open();
        System.out.println("Taking photo");
        new Thread(() -> {
            while (true) {
                try {
                    lastImage = webcam.getImage();
                    updateImageView();
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
        try {
            if (lastImage != null) {
                String filePath = "userphotos/";
                File folder = new File(filePath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                int count = 1;
                String fileName = "photo" + count + ".png";
                File file = new File(folder, fileName);
                while (file.exists()) {
                    count++;
                    fileName = "photo" + count + ".png";
                    file = new File(folder, fileName);
                }

                ImageIO.write(lastImage, "PNG", file);
                System.out.println("Photo saved successfully!");
            } else {
                System.out.println("No image to save!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save photo!");
        }
    }

    public void stop() {
        webcam.close();
    }
}


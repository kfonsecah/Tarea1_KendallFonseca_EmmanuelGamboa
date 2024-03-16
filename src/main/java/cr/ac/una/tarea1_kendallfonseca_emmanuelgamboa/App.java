package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage, null);
        stage.setTitle("UNA");
        FlowController.getInstance().goMain();


    }


    public static void main(String[] args) {
        launch();
    }

}
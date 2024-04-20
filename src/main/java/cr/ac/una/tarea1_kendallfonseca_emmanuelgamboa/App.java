package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller.PrincipalController;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Cooperative;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
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
    private static String accessParameter = "";

    @Override
    public void start(Stage stage) throws IOException {
        Cooperative cooperative = new Cooperative();
        cooperative.loadFromTxtFile();
        AppContext.getInstance().set("cooperative", cooperative);
        FlowController.getInstance().InitializeFlow(stage, null, accessParameter);
        FlowController.getInstance().goMain("PrincipalView");
    }
    public static void main(String[] args) {
        if (args.length>0){
            accessParameter = args[0];
        }

        launch();
    }

}

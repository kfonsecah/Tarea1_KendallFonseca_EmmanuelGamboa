
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller.PrincipalController;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Cooperative;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.FlowController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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

        // Permitir el redimensionamiento de la ventana
        stage.setResizable(true);

        // Inicializar el flujo de la aplicacion y establecer la vista principal
        FlowController.getInstance().InitializeFlow(stage, null);

        if (accessParameter.equals("A")) {
            FlowController.getInstance().goMain("AdminView");
        } else if (accessParameter.equals("W")) {
            FlowController.getInstance().goMain("WorkerView");
        } else if (accessParameter.equals("M")) {
            FlowController.getInstance().goMain("AssociateView");
        } else
            FlowController.getInstance().goMain("PrincipalView");


        // Asegurarse de que la escena se ajuste al tamaño de la ventana
        Scene scene = stage.getScene();
        if (scene != null) {
            scene.setFill(null); // Opcional: Hacer que el fondo de la escena sea transparente
            stage.sizeToScene(); // Ajustar el tamaño de la ventana a la escena
        }

        // Mostrar la ventana
        stage.show();
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            accessParameter = args[0];
        }

        launch();
    }
}
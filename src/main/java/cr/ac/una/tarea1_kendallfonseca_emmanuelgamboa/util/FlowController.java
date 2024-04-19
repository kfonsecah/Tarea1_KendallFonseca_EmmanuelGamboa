/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.App;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller.Controller;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Cooperative;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FlowController {

    private static FlowController INSTANCE = null;
    private static Stage mainStage;
    private static ResourceBundle idioma;
    private static HashMap<String, FXMLLoader> loaders = new HashMap<>();
    Cooperative cooperative = (Cooperative) AppContext.getInstance().get("cooperative");


    private FlowController() {


    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (FlowController.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FlowController();
                }
            }
        }
    }

    public static FlowController getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void InitializeFlow(Stage stage, ResourceBundle idioma, String accessParameter) {
        getInstance();

        this.mainStage = stage;
        this.idioma = idioma;
        goViewByAccess(accessParameter);
    }

    private void goViewByAccess(String access) {
        if (access.equals("A")) {
            goMain("AssociateView");
        }
        if (access.equals("F")) {
            goMain("WorkerView");
        }
        if (access.equals("P")) {
            goMain("AdminView");
        }
    }

    private FXMLLoader getLoader(String name) {
        FXMLLoader loader = loaders.get(name);
        if (loader == null) {
            synchronized (FlowController.class) {
                if (loader == null) {
                    try {
                        loader = new FXMLLoader(App.class.getResource("view/" + name + ".fxml"), this.idioma);
                        loader.load();
                        loaders.put(name, loader);

                    } catch (Exception ex) {
                        loader = null;
                        java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE, "Creando loader [" + name + "].", ex);
                    }
                }
            }
        }
        return loader;
    }


    public void goMain(String viewname) {
        try {
            // Load the FXML file and set it as the scene's root
            FXMLLoader loader = new FXMLLoader(App.class.getResource("view/" + viewname + ".fxml"), this.idioma);
            loader.getNamespace().clear(); // Clear the FXMLLoader cache
            Parent root = loader.load();
            Scene scene = new Scene(root);
            //MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
            // Add the MaterialFX theme to the scene


            // Set the scene and show the stage
            this.mainStage.setScene(scene);
            this.mainStage.show();
            String logoPath = "src/main/resources/cr/ac/una/tarea1_kendallfonseca_emmanuelgamboa/resources/newLogo.png";
            File logoFile = new File(logoPath);
            Image logoImage = new Image(logoFile.toURI().toString());

            this.mainStage.getIcons().clear();
            this.mainStage.getIcons().add(cooperative.getLogo());

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE, "Error inicializando la vista base.", ex);
        }
    }

    public void goView(String viewName) {
        goView(viewName, "Center", null);
    }

    public void goView(String viewName, String accion) {
        goView(viewName, "Center", accion);
    }

    public void goView(String viewName, String location, String accion) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.setAccion(accion);
        controller.initialize();
        Stage stage = controller.getStage();
        if (stage == null) {
            stage = this.mainStage;
            controller.setStage(stage);
        }
        switch (location) {
            case "Center":
                ((VBox) ((BorderPane) stage.getScene().getRoot()).getCenter()).getChildren().clear();
                ((VBox) ((BorderPane) stage.getScene().getRoot()).getCenter()).getChildren().add(loader.getRoot());
                break;
            case "Top":
                break;
            case "Bottom":
                break;
            case "Right":
                break;
            case "Left":
                break;
            default:
                break;
        }
    }

    public void goViewInStage(String viewName, Stage stage) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.setStage(stage);
        stage.getScene().setRoot(loader.getRoot());
    }

    public void goViewInWindow(String viewName) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.initialize();
        Stage stage = new Stage();
        //stage.getIcons().add(new Image("cr/ac/una/tareap/resources/logo.png"));
        stage.setTitle("UNA");
        stage.setOnHidden((WindowEvent event) -> {
            controller.getStage().getScene().setRoot(new Pane());
            controller.setStage(null);
        });
        controller.setStage(stage);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    public void goViewInWindowModal(String viewName, Stage parentStage, Boolean resizable) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.initialize();
        Stage stage = new Stage();
        //stage.getIcons().add(new Image("cr/ac/una/tareaprogra/resources/logo.png"));
        //stage.setTitle("UNA PLANILLA");

        stage.getIcons().clear();


        stage.setResizable(resizable);
        stage.setOnHidden((WindowEvent event) -> {
            controller.getStage().getScene().setRoot(new Pane());
            controller.setStage(null);
        });
        controller.setStage(stage);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parentStage);
        stage.centerOnScreen();
        stage.showAndWait();

    }

    public Controller getController(String viewName) {
        return getLoader(viewName).getController();
    }

    public static void setIdioma(ResourceBundle idioma) {
        FlowController.idioma = idioma;
    }

    public void initialize() {
        this.loaders.clear();
    }

    public void salir() {
        this.mainStage.close();
    }
}

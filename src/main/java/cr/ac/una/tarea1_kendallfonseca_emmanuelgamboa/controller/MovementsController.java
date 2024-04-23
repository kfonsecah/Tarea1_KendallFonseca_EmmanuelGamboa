package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MovementsController extends Controller implements Initializable {

    @FXML
    private TableView<?> TableViewMovements;

    @FXML
    private MFXButton btnSearch;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tableViewAccounts;

    @FXML
    private Label txtAge;

    @FXML
    private Label txtFolio;

    @FXML
    private Label txtLastName;

    @FXML
    private Label txtName;

    @FXML
    private MFXTextField txtSearch;

    @FXML
    void onActionBtnSearch(ActionEvent event) {

    }

    @FXML
    void onActionDetail(ActionEvent event) {

    }

    @FXML
    void onActionResumed(ActionEvent event) {

    }

    @Override
    public void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSpinner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AssociateDepositsController extends Controller implements Initializable{
    @Override
    public void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private MFXSpinner<?> addFiftyC;

    @FXML
    private MFXSpinner<?> addFiveBill;

    @FXML
    private MFXSpinner<?> addFiveC;

    @FXML
    private MFXSpinner<?> addFiveHundredC;

    @FXML
    private MFXSpinner<?> addOneBill;

    @FXML
    private MFXSpinner<?> addOneHundredC;

    @FXML
    private MFXSpinner<?> addTenBill;

    @FXML
    private MFXSpinner<?> addTenC;

    @FXML
    private MFXSpinner<?> addTwentyBill;

    @FXML
    private MFXSpinner<?> addTwentyC;

    @FXML
    private MFXSpinner<?> addTwoBill;

    @FXML
    private MFXButton btnDepositsDelete;

    @FXML
    private MFXButton btnDepositsRequest;

    @FXML
    private MFXButton btnDepositsSearch;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtFolioSeaarch;

    @FXML
    private Label txtTotal;

    @FXML
    private TableView<?> userDepositsList;

    @FXML
    private TableView<?> userFolioList;

    @FXML
    void handleDeleteButtonAction(ActionEvent event) {

    }

    @FXML
    void handleSearchButtonAction(ActionEvent event) {

    }

    @FXML
    void onActionRequest(ActionEvent event) {

    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSpinner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author gambo
 */
public class WithdrawDepositsController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private TableView<?> userDepositsList;

    @FXML
    private TableView<?> userFolioList;

    @FXML
    private MFXButton btnDepositsDelete;

    @FXML
    private MFXButton btnDepositsRequest;

    @FXML
    private MFXButton btnDepositsSearch;

    @FXML
    private AnchorPane root;

    @Override
    public void initialize() {

    }
}

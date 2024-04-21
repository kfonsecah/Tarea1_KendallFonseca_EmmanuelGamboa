/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSpinner;
import io.github.palexdev.materialfx.controls.models.spinner.IntegerSpinnerModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        configureSpinners();


    }

    @Override
    public void initialize() {

    }

    @FXML
    private MFXSpinner<Integer> addFiftyC;

    @FXML
    private MFXSpinner<Integer> addFiveBill;

    @FXML
    private MFXSpinner<Integer> addFiveC;

    @FXML
    private MFXSpinner<Integer> addFiveHundredC;

    @FXML
    private MFXSpinner<Integer> addOneBill;

    @FXML
    private MFXSpinner<Integer> addOneHundredC;

    @FXML
    private MFXSpinner<Integer> addTenBill;

    @FXML
    private MFXSpinner<Integer> addTenC;

    @FXML
    private MFXSpinner<Integer> addTwentyBill;

    @FXML
    private MFXSpinner<Integer> addTwentyC;

    @FXML
    private MFXSpinner<Integer> addTwoBill;

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




    private void configureSpinners() {
        // Create SpinnerModels for each spinner
        IntegerSpinnerModel fiftyCModel = new IntegerSpinnerModel();
        IntegerSpinnerModel fiveBillModel = new IntegerSpinnerModel();
        IntegerSpinnerModel fiveCModel = new IntegerSpinnerModel();
        IntegerSpinnerModel fiveHundredCModel = new IntegerSpinnerModel();
        IntegerSpinnerModel oneBillModel = new IntegerSpinnerModel();
        IntegerSpinnerModel oneHundredCModel = new IntegerSpinnerModel();
        IntegerSpinnerModel tenBillModel = new IntegerSpinnerModel();
        IntegerSpinnerModel tenCModel = new IntegerSpinnerModel();
        IntegerSpinnerModel twentyBillModel = new IntegerSpinnerModel();
        IntegerSpinnerModel twentyCModel = new IntegerSpinnerModel();
        IntegerSpinnerModel twoBillModel = new IntegerSpinnerModel();

        // Set the SpinnerModels to their respective MFXSpinners
        addFiftyC.setSpinnerModel(fiftyCModel);
        addFiveBill.setSpinnerModel(fiveBillModel);
        addFiveC.setSpinnerModel(fiveCModel);
        addFiveHundredC.setSpinnerModel(fiveHundredCModel);
        addOneBill.setSpinnerModel(oneBillModel);
        addOneHundredC.setSpinnerModel(oneHundredCModel);
        addTenBill.setSpinnerModel(tenBillModel);
        addTenC.setSpinnerModel(tenCModel);
        addTwentyBill.setSpinnerModel(twentyBillModel);
        addTwentyC.setSpinnerModel(twentyCModel);
        addTwoBill.setSpinnerModel(twoBillModel);
    }

    }


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.net.URL;
import java.security.cert.PolicyNode;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Deposits;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckListView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXCheckListCell;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Kendall Fonseca
 */
public class DepositsController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDeposits();
        initializeTableView();
        // TODO
    }

    private void loadDeposits() {
        ObservableList<Deposits> deposits = AppContext.getInstance().getDeposits();

        pendingDeposits.setItems(deposits);


    }



    private void initializeTableView() {
        // Initialize columns
        MFXTableColumn<Deposits> accountNumberColumn = new MFXTableColumn<>("Account Number");
        MFXTableColumn<Deposits> accountTypeColumn = new MFXTableColumn<>("Account Type");

        MFXTableColumn<Deposits> customerNameColumn = new MFXTableColumn<>("Customer Name");
        MFXTableColumn<Deposits> amountColumn = new MFXTableColumn<>("Amount");

        // Set cell value factories






        // Set items to the table view
        ObservableList<Deposits> deposits = AppContext.getInstance().getDeposits();
        pendingDeposits.setItems(deposits);
    }









    // Method to retrieve deposit





    @Override
    public void initialize() {
        // TODO
    }

    @FXML
    private MFXButton acceptDeposits;

    @FXML
    private MFXTableView<Deposits> pendingDeposits;

    @FXML
    private MFXButton removeDeposits;

    @FXML
    private AnchorPane root;

    @FXML
    void onActionAcceptDeposits(ActionEvent event) {

    }

    @FXML
    void onActionRemoveDeposits(ActionEvent event) {

    }


}

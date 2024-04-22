package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Deposits;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class DepositsController extends Controller implements Initializable {

    @FXML
    private ListView<Deposits> pendingDeposits;

    @FXML
    private CheckBox ListCell;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDeposits();
    }

    private void loadDeposits() {
        ObservableList<Deposits> deposits = AppContext.getDeposits();

        pendingDeposits.setItems(deposits);
        pendingDeposits.setCellFactory(new Callback<ListView<Deposits>, ListCell<Deposits>>() {
            @Override
            public ListCell<Deposits> call(ListView<Deposits> listView) {
                return new CheckBoxListCell();
            }
        });
    }

    // Define a custom ListCell to display CheckBoxes for each deposit
    private static class CheckBoxListCell extends ListCell<Deposits> {
        private final CheckBox checkBox;
        private final Label label;

        public CheckBoxListCell() {
            checkBox = new CheckBox();
            label = new Label();
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (getItem() != null) {
                    getItem().setSelected(newValue);
                }
            });
        }

        @Override
        protected void updateItem(Deposits deposit, boolean empty) {
            super.updateItem(deposit, empty);
            if (empty || deposit == null) {
                setGraphic(null);
            } else {
                // Set the text of the label to display total amount and account type
                label.setText("Total: " + deposit.getMoneda() + " | Tipo de cuenta: " + deposit.getTipoCuenta()+" | Folio del Asociado: "+deposit.getFolio());
                checkBox.setSelected(deposit.isSelected());

                HBox hbox = new HBox(checkBox, label);
                hbox.setSpacing(10); // Adjust spacing between checkBox and label
                setGraphic(hbox);
            }
        }
    }


    @Override
    public void initialize() {
        // TODO
    }

    @FXML
    private AnchorPane root;

    @FXML
    void onActionAcceptDeposits(ActionEvent event) {
        // TODO: Implement accepting deposits
    }

    @FXML
    void onActionRemoveDeposits(ActionEvent event) {
        // TODO: Implement removing deposits
    }
}

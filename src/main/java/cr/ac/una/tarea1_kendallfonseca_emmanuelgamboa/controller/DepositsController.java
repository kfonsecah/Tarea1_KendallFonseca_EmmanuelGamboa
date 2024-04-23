package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Deposits;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AccountUser;
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
        ObservableList<Deposits> allDeposits = AppContext.getDeposits();

        // Filtrar los depósitos que tienen inProcess=true
        ObservableList<Deposits> filteredDeposits = allDeposits.filtered(Deposits::isInProcess);

        pendingDeposits.setItems(filteredDeposits);
        pendingDeposits.setCellFactory(new Callback<ListView<Deposits>, ListCell<Deposits>>() {
            @Override
            public ListCell<Deposits> call(ListView<Deposits> listView) {
                return new CheckBoxListCell();
            }
        });
    }


    // Definir una account Ceel para mostrar un CheckBox y un Label
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
                label.setText("Total: " + deposit.getMoneda() + " | Tipo de cuenta: " + deposit.getTipoCuenta() + " | Folio del Asociado: " + deposit.getFolio() + " | " + deposit.getTipoMovimiento());

                // Establecer el color del texto basado en el tipo de movimiento
                if ("Deposito".equals(deposit.getTipoMovimiento())) {
                    label.setStyle("-fx-text-fill: green;");
                } else if ("Retiro".equals(deposit.getTipoMovimiento())) {
                    label.setStyle("-fx-text-fill: red;");
                } else {
                    label.setStyle(""); // Revertir a color predeterminado
                }

                checkBox.setSelected(deposit.isSelected());

                HBox hbox = new HBox(checkBox, label);
                hbox.setSpacing(10);
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
    void onActionAcceptDeposits(ActionEvent event) throws IOException {
        // Obtener el depósito seleccionado
        Deposits selectedDeposit = pendingDeposits.getSelectionModel().getSelectedItem();
        System.out.println("Deposito seleccionado: "+selectedDeposit);
        if (selectedDeposit != null) {
            // Realizar el depósito enviando la cantidad de moneda como monto
            realizarDeposito(selectedDeposit.getFolio(), selectedDeposit.getTipoCuenta(), selectedDeposit.getMoneda());

        }
    }

    private void realizarDeposito(String folio, String tipoCuenta, int monto) throws IOException {
        AccountUser accountUser = AppContext.getInstance().getAccountUser();
        Deposits selectedDeposit = pendingDeposits.getSelectionModel().getSelectedItem();

        // Realizar el depósito
        accountUser.realizarDeposito(folio, tipoCuenta, monto);

        // Cambiar el estado inProcess del depósito seleccionado a false
        if (selectedDeposit != null) {
            accountUser.setDepositInProcessFalse(selectedDeposit);
        }

        // Actualizar la lista de depósitos después de realizar el depósito
        loadDeposits();
    }



    @FXML
    void onActionRemoveDeposits(ActionEvent event) {
        // TODO
    }
}

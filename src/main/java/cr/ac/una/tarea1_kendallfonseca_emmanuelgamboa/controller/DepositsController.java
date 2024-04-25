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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class DepositsController extends Controller implements Initializable {

    @FXML
    private ListView<Deposits> pendingDeposits;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDeposits();
    }

    @Override
    public void initialize() {

    }

    private void loadDeposits() {
        ObservableList<Deposits> allDeposits = AppContext.getDeposits();

        // Filtrar los depósitos que tienen inProcess=true
        ObservableList<Deposits> filteredDeposits = allDeposits.filtered(Deposits::isInProcess);

        pendingDeposits.setItems(filteredDeposits);
        pendingDeposits.setCellFactory(param -> new ListCell<Deposits>() {
            private final Label label;

            {
                label = new Label();
            }

            @Override
            protected void updateItem(Deposits deposit, boolean empty) {
                super.updateItem(deposit, empty);
                if (empty || deposit == null) {
                    setText(null);
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

                    setGraphic(label);
                }
            }
        });
    }

    @FXML
    void onActionAcceptDeposits(ActionEvent event) throws IOException {
        // Obtener el depósito seleccionado
        Deposits selectedDeposit = pendingDeposits.getSelectionModel().getSelectedItem();
        System.out.println("Depósito seleccionado: " + selectedDeposit);
        if (selectedDeposit != null) {
            if ("Deposito".equals(selectedDeposit.getTipoMovimiento())) {
                // Realizar el depósito
                realizarDeposito(selectedDeposit);
            } else if ("Retiro".equals(selectedDeposit.getTipoMovimiento())) {
                // Realizar el retiro
                realizarRetiro(selectedDeposit);
            }

            // Eliminar el depósito de la lista de depósitos
            AppContext.getDeposits().remove(selectedDeposit);

            // Recargar la lista de depósitos
            loadDeposits();
        }
    }

    public void realizarRetiro(Deposits selectedDeposit) {
        AccountUser accountUser = AppContext.getInstance().getAccountUser();

        try {
            // Realizar el retiro
            accountUser.realizarRetiro(selectedDeposit.getFolio(), selectedDeposit.getTipoCuenta(), selectedDeposit.getMoneda());

            // Cambiar el estado inProcess del depósito seleccionado a false
            accountUser.setDepositInProcessFalseForDeposit(selectedDeposit);

            System.out.println("Retiro realizado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al realizar el retiro: " + e.getMessage());
        }
    }

    public void realizarDeposito(Deposits selectedDeposit) {
        AccountUser accountUser = AppContext.getInstance().getAccountUser();

        try {
            // Realizar el depósito
            accountUser.realizarDeposito(selectedDeposit.getFolio(), selectedDeposit.getTipoCuenta(), selectedDeposit.getMoneda());

            // Cambiar el estado inProcess del depósito seleccionado a false
            accountUser.setDepositInProcessFalseForDeposit(selectedDeposit);

            System.out.println("Depósito realizado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al realizar el depósito: " + e.getMessage());
        }
    }

    @FXML
    void onActionRemoveDeposits(ActionEvent event) {
        // Obtener el depósito seleccionado
        Deposits selectedDeposit = pendingDeposits.getSelectionModel().getSelectedItem();
        System.out.println("Deposito seleccionado: " + selectedDeposit);
        if (selectedDeposit != null) {
            try {
                // Eliminar el depósito del archivo JSON
                AppContext.removeDepositFromJsonFile(selectedDeposit);

                // Eliminar el depósito de la lista de depósitos en memoria
                AppContext.getDeposits().remove(selectedDeposit);

                // Recargar la lista de depósitos
                loadDeposits();
                System.out.println("Depósito eliminado correctamente.");
            } catch (IOException e) {
                System.out.println("Error al eliminar el depósito: " + e.getMessage());
            }
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AccountUser;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.Mensaje;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Deposits;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSpinner;
import io.github.palexdev.materialfx.controls.models.spinner.IntegerSpinnerModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author gambo
 */
public class WithdrawDepositsController extends Controller implements Initializable {


    /**
     * Initializes the controller class.
     */

    @FXML
    private ImageView droppedCoins;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Ocultar el GIF
        droppedCoins.setVisible(false);
        //poner pausa al gif


        configureSpinners();

        initializeTableView();

        enableSpinners(false);


    }

    @Override
    public void initialize() {

    }
    private ObservableList<Account> accountsByFolioList;
    AccountUser accountUser = new AccountUser();


    @FXML
    private TextField txtFolioSeaarch;

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
    private TableView<Deposits> userDepositsList;

    @FXML
    private TableView<Account> userFolioList;

    @FXML
    private MFXButton btnDepositsDelete;

    @FXML
    private MFXButton btnDepositsRequest;

    @FXML
    private MFXButton btnDepositsSearch;

    @FXML
    private MFXButton btnWhithdraw;

    @FXML
    private Label txtTotal;

    @FXML
    private AnchorPane root;






    private void configureSpinners() {


        // Create SpinnerModels for each spinner
        IntegerSpinnerModel fiftyCModel = new IntegerSpinnerModel();
        fiftyCModel.setMax(200);
        fiftyCModel.setMin(0);


        IntegerSpinnerModel fiveBillModel = new IntegerSpinnerModel();
        fiveBillModel.setMax(100);
          fiveBillModel.setMin(0);


        IntegerSpinnerModel fiveCModel = new IntegerSpinnerModel();
           fiveCModel.setMax(100);
            fiveCModel.setMin(0);

        IntegerSpinnerModel fiveHundredCModel = new IntegerSpinnerModel();
        fiveHundredCModel.setMax(100);
        fiveHundredCModel.setMin(0);
        IntegerSpinnerModel oneBillModel = new IntegerSpinnerModel();
        oneBillModel.setMax(100);
        oneBillModel.setMin(0);
        IntegerSpinnerModel oneHundredCModel = new IntegerSpinnerModel();
        oneHundredCModel.setMax(100);
        oneHundredCModel.setMin(0);
        IntegerSpinnerModel tenBillModel = new IntegerSpinnerModel();
        tenBillModel.setMax(100);
        tenBillModel.setMin(0);
        IntegerSpinnerModel tenCModel = new IntegerSpinnerModel();
        tenCModel.setMax(100);
        tenCModel.setMin(0);
        IntegerSpinnerModel twentyBillModel = new IntegerSpinnerModel();
        twentyBillModel.setMax(100);
        twentyBillModel.setMin(0);
        IntegerSpinnerModel twentyCModel = new IntegerSpinnerModel();
        twentyCModel.setMax(100);
        twentyCModel.setMin(0);
        IntegerSpinnerModel twoBillModel = new IntegerSpinnerModel();
        twoBillModel.setMax(100);
        twoBillModel.setMin(0);



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


            if (txtFolioSeaarch.getText().isEmpty()) {
                btnDepositsRequest.setDisable(true);
            } else {
                btnDepositsRequest.setDisable(false);
            }
            addFiveC.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue != null) {
                    uptadeTable(5, newValue, oldValue);
                }
            });

            addTenC.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue != null) {
                    uptadeTable(10, newValue, oldValue);
                }
            });

            addTwentyC.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue != null) {
                    uptadeTable(25, newValue, oldValue);
                }
            });

            addFiftyC.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue != null) {
                    uptadeTable(50, newValue, oldValue);
                }
            });

            addOneHundredC.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue != null) {
                    uptadeTable(100, newValue, oldValue);
                }
            });
            addFiveHundredC.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue != null) {
                    uptadeTable(500, newValue, oldValue);
                }
            });
            addOneBill.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue != null) {
                    uptadeTable(1000, newValue, oldValue);
                }
            });
            addTwoBill.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue != null) {
                    uptadeTable(2000, newValue, oldValue);
                }
            });
            addFiveBill.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue != null) {
                    uptadeTable(5000, newValue, oldValue);
                }
            });
            addTenBill.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue != null) {
                    uptadeTable(10000, newValue, oldValue);
                }
            });
            addTwentyBill.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue != null) {
                    uptadeTable(20000, newValue, oldValue);
                }
            });

            addFiftyC.setSpinnerModel(fiftyCModel);


    }

    private void initializeTableView() {

        TableColumn<Deposits, String> monedaCol = new TableColumn<>("Moneda");
        monedaCol.setCellValueFactory(new PropertyValueFactory<>("moneda"));

        TableColumn<Deposits, Integer> cantidadCol = new TableColumn<>("Cantidad");
        cantidadCol.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        userDepositsList.getColumns().addAll(monedaCol, cantidadCol);

        TableColumn<Account, String> tipoCuentaCol = new TableColumn<>("Tipo de Cuenta");
        tipoCuentaCol.setCellValueFactory(new PropertyValueFactory<>("accountType"));

        TableColumn<Account, Double> saldoCol = new TableColumn<>("Saldo");
        saldoCol.setCellValueFactory(new PropertyValueFactory<>("balance"));


        userFolioList.getColumns().addAll(tipoCuentaCol, saldoCol);
    }


    private void uptadeTable(int moneda, Integer newValue, Integer oldValue) {
        boolean monedaEncontrada = false;

        for (Deposits deposit : userDepositsList.getItems()) {
            if (deposit.getMoneda() == moneda) {
                if (newValue > oldValue) {
                    deposit.setCantidad(deposit.getCantidad() + (newValue - oldValue));
                } else if (newValue < oldValue) {
                    deposit.setCantidad(deposit.getCantidad() - (oldValue - newValue));
                    if (deposit.getCantidad() == 0) {
                        userDepositsList.getItems().remove(deposit);
                    }
                }
                monedaEncontrada = true;
                break;
            }
        }

        if (!monedaEncontrada && newValue > 0) {
            String folio = accountsByFolioList.get(0).getFolio(); // Assuming only one account is selected
            String accountType = accountsByFolioList.get(0).getAccountType(); // Assuming only one account is selected
            Deposits deposit = new Deposits(moneda, newValue, folio, accountType,true , "Retiro", false, LocalDateTime.now());
            userDepositsList.getItems().add(deposit);
        }

        calculateTotal();
    }

    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
       cleanTable();
         resetSpinners();
    }

    private void cleanTable() {
        userDepositsList.getItems().clear();
    }
    private void resetSpinners() {
        addFiftyC.setValue(0);
        addFiveBill.setValue(0);
        addFiveC.setValue(0);
        addFiveHundredC.setValue(0);
        addOneBill.setValue(0);
        addOneHundredC.setValue(0);
        addTenBill.setValue(0);
        addTenC.setValue(0);
        addTwentyBill.setValue(0);
        addTwentyC.setValue(0);
        addTwoBill.setValue(0);
    }

    private void calculateTotal() {
        int total = 0;
        for (Deposits deposit : userDepositsList.getItems()) {
            total += deposit.getMoneda() * deposit.getCantidad();
        }
        txtTotal.setText("Total: ₡ " + total);

    }


    @FXML
    private void handleSearchButtonAction(ActionEvent event) {
        String folio = txtFolioSeaarch.getText();
        if (!folio.isEmpty()) {
            // Verificar si accountUser es null y crear una instancia si es necesario
            if (accountUser == null) {
                accountUser = new AccountUser();
            }

            // Obtener la lista de cuentas asociadas al folio
            accountsByFolioList = accountUser.getAccountsByFolio(folio);

            // Verificar si la lista no está vacía antes de agregarla a la tabla
            if (!accountsByFolioList.isEmpty()) {
                // Limpiar la tabla antes de agregar nuevos elementos
                userFolioList.getItems().clear();
                // Agregar las cuentas a la tabla
                userFolioList.setItems(accountsByFolioList);

                // Refrescar la TableView para asegurarse de que se actualice en la interfaz de usuario
                userFolioList.refresh();

                // Habilitar los spinners ya que hay una cuenta seleccionada
                enableSpinners(true);

                // Habilitar el botón "Solicitar" ya que hay una cuenta seleccionada
                btnDepositsRequest.setDisable(false);
            } else {
                // Si no se encontraron cuentas, mostrar un mensaje
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "No se encontraron cuentas asociadas al folio proporcionado.");

                // Deshabilitar los spinners ya que no hay una cuenta seleccionada
                enableSpinners(false);

                // Deshabilitar el botón "Solicitar" ya que no hay una cuenta seleccionada
                btnDepositsRequest.setDisable(true);
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor, ingrese un folio antes de continuar.");

            // Deshabilitar los spinners ya que no hay una cuenta seleccionada
            enableSpinners(false);

            // Deshabilitar el botón "Solicitar" ya que no hay una cuenta seleccionada
            btnDepositsRequest.setDisable(true);
        }
    }
    private void enableSpinners(boolean enable) {
        addFiftyC.setDisable(!enable);
        addFiveBill.setDisable(!enable);
        addFiveC.setDisable(!enable);
        addFiveHundredC.setDisable(!enable);
        addOneBill.setDisable(!enable);
        addOneHundredC.setDisable(!enable);
        addTenBill.setDisable(!enable);
        addTenC.setDisable(!enable);
        addTwentyBill.setDisable(!enable);
        addTwentyC.setDisable(!enable);
        addTwoBill.setDisable(!enable);
    }

    public void updateTableWithNewAccount(Account newAccount) {
        if (accountsByFolioList != null) {
            accountsByFolioList.add(newAccount);
            userFolioList.setItems(accountsByFolioList);
        }
    }

    @FXML
    public void onActionRequest(ActionEvent event) {
        droppedCoins.setVisible(true);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.7), new KeyValue(droppedCoins.visibleProperty(), false))
        );
        timeline.play();
        Account selectedAccount = userFolioList.getSelectionModel().getSelectedItem();

        // Verificar si hay una cuenta seleccionada y si el total es mayor que cero
        if (selectedAccount != null && getTotal() > 0) {
            // Realizar el depósito
            makeDeposit(selectedAccount);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor, seleccione una cuenta y agregue al menos una moneda antes de continuar.");
        }
    }
    private int getTotal() {
        int total = 0;
        for (Deposits deposit : userDepositsList.getItems()) {
            total += deposit.getMoneda() * deposit.getCantidad();
        }
        return total;
    }

    private void makeDeposit(Account selectedAccount) {
        // Obtener la fecha y hora actual
        LocalDateTime dateTime = LocalDateTime.now();

        // Extract folio and account type from the selected account
        String folio = selectedAccount.getFolio();
        String accountType = selectedAccount.getAccountType();

        // Extract the total amount from txtTotal
        int total = getTotal();

        // Create a new deposit object with date and time
        Deposits newDeposit = new Deposits(total, 1, folio, accountType, true, "Deposito", false, dateTime);

        // Add the new deposit to your data store
        try {
            AppContext.addDepositToJsonFile(newDeposit);
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Éxito", root.getScene().getWindow(), "El deposito se realizó con éxito.");
        } catch (IOException e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Ocurrió un error al realizar el deposito.");
            e.printStackTrace();
        }
    }

    @FXML
    void onActionWithdraw(ActionEvent event) {
        Account selectedAccount = userFolioList.getSelectionModel().getSelectedItem();

        // Verificar si hay una cuenta seleccionada
        if (selectedAccount != null) {
            // Obtener el total del retiro
            int total = getTotal();

            // Verificar si el total es mayor que cero
            if (total > 0) {
                // Obtener el saldo de la cuenta seleccionada
                double accountBalance = selectedAccount.getBalance();

                // Verificar si el total es mayor que el saldo de la cuenta
                if (total > accountBalance) {
                    // Mostrar un mensaje de error si el total es mayor que el saldo
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "El total del retiro supera el saldo de la cuenta seleccionada.");
                } else {
                    // Realizar el retiro si el total es menor o igual que el saldo de la cuenta
                    makeWithdrawal(selectedAccount, total);
                }
            } else {
                // Mostrar un mensaje de error si el total es igual a cero
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "El total del retiro debe ser mayor que cero.");
            }
        } else {
            // Mostrar un mensaje de error si no hay una cuenta seleccionada
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Por favor, seleccione una cuenta antes de realizar un retiro.");
        }
    }


    private void makeWithdrawal(Account selectedAccount, int total) {
        // Extract folio and account type from the selected account
        String folio = selectedAccount.getFolio();
        String accountType = selectedAccount.getAccountType();

        // Create a new withdrawal object
        Deposits newWithdrawal = new Deposits(total, 1, folio, accountType, true, "Retiro", false, LocalDateTime.now());

        // Add the new withdrawal to your data store
        try {
            AppContext.addDepositToJsonFile(newWithdrawal);
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Éxito", root.getScene().getWindow(), "El retiro se realizó con éxito.");
        } catch (IOException e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Ocurrió un error al realizar el retiro.");
            e.printStackTrace();
        }

        // Limpiar la tabla y reiniciar los spinners
        resetSpinners();
        cleanTable();
        userFolioList.getItems().clear();
        updateTableWithNewAccount(selectedAccount);
    }


}







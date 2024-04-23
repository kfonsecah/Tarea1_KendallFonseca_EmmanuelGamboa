/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
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


        //Crear los modelos de los spinners
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


        // Asignar los modelos a los spinners
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
                updateTable(5, newValue, oldValue);
            }
        });

        addTenC.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                updateTable(10, newValue, oldValue);
            }
        });

        addTwentyC.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                updateTable(25, newValue, oldValue);
            }
        });

        addFiftyC.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                updateTable(50, newValue, oldValue);
            }
        });

        addOneHundredC.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                updateTable(100, newValue, oldValue);
            }
        });
        addFiveHundredC.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                updateTable(500, newValue, oldValue);
            }
        });
        addOneBill.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                updateTable(1000, newValue, oldValue);
            }
        });
        addTwoBill.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                updateTable(2000, newValue, oldValue);
            }
        });
        addFiveBill.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                updateTable(5000, newValue, oldValue);
            }
        });
        addTenBill.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                updateTable(10000, newValue, oldValue);
            }
        });
        addTwentyBill.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                updateTable(20000, newValue, oldValue);
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



    private void updateTable(int moneda, Integer newValue, Integer oldValue) {
        boolean monedaEncontrada = false;

        // Buscar el depósito correspondiente a la moneda
        for (Deposits deposit : userDepositsList.getItems()) {
            if (deposit.getMoneda() == moneda) {
                monedaEncontrada = true;
                if (newValue > oldValue) {
                    deposit.setCantidad(newValue); // Actualizar la cantidad directamente
                } else if (newValue < oldValue) {
                    deposit.setCantidad(newValue); // Actualizar la cantidad directamente
                }
                break;
            }
        }

        // Si no se encuentra la moneda y el nuevo valor es mayor que cero, agregar un nuevo depósito
        if (!monedaEncontrada && newValue > 0) {
            // Crear un nuevo depósito con la moneda y la cantidad
            Deposits newDeposit = new Deposits(moneda, newValue, "", "", true, "", false, "");
            userDepositsList.getItems().add(newDeposit);
        }

        // Calcular el total después de actualizar la tabla
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
        // Obtener la fecha y hora actual como String
        String dateTimeString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String folio = selectedAccount.getFolio();
        String accountType = selectedAccount.getAccountType();
        int total = getTotal();

        // Crear un nuevo depósito con el total del depósito, el folio y el tipo de cuenta
        Deposits newDeposit = new Deposits(total, 1, folio, accountType, true, "Deposito", false, dateTimeString);

        // Agregar el depósito al archivo JSON
        try {
            AppContext.addDepositToJsonFile(newDeposit);
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Éxito", root.getScene().getWindow(), "El depósito se realizó con éxito.");
        } catch (IOException e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error", root.getScene().getWindow(), "Ocurrió un error al realizar el depósito.");
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
        // Obtener la fecha y hora actual como String
        String dateTimeString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Obtener el folio y el tipo de cuenta de la cuenta seleccionada
        String folio = selectedAccount.getFolio();
        String accountType = selectedAccount.getAccountType();

        // Crear un nuevo retiro con el total del retiro, el folio y el tipo de cuenta
        Deposits newWithdrawal = new Deposits(total, 1, folio, accountType, true, "Retiro", false, dateTimeString);

        // Agregar el retiro al archivo JSON
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







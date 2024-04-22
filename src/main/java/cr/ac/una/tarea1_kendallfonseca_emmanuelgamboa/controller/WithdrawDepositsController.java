/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Account;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AccountUser;
import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.util.AppContext;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model.Deposits;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSpinner;
import io.github.palexdev.materialfx.controls.models.spinner.IntegerSpinnerModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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


         // Crea una instancia de AccountUser
        // Pasa el objeto AccountUser al constructor

        configureSpinners();

        initializeTableView();


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
            Deposits deposit = new Deposits(moneda, newValue, folio, accountType,true , "Retiro", false);
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
            } else {
                // Si no se encontraron cuentas, mostrar un mensaje
                System.out.println("No se encontraron cuentas asociadas al folio: " + folio);
            }
        } else {
            System.out.println("Folio vacío");
        }
    }

    public void updateTableWithNewAccount(Account newAccount) {
        if (accountsByFolioList != null) {
            accountsByFolioList.add(newAccount);
            userFolioList.setItems(accountsByFolioList);
        }
    }

    @FXML
    public void onActionRequest(ActionEvent event) {
        // Get the selected account from userFolioList
        Account selectedAccount = userFolioList.getSelectionModel().getSelectedItem();

        if (selectedAccount != null) {
            // Extract folio and account type from the selected account
            String folio = selectedAccount.getFolio();
            String accountType = selectedAccount.getAccountType();

            // Extract the total amount from txtTotal
            String totalText = txtTotal.getText();
            int total = Integer.parseInt(totalText.replaceAll("\\D", ""));

            // Create a new deposit object
            Deposits newDeposit = new Deposits(total, 1, folio, accountType,true, "Retiro", false);

            // Add the new deposit to your data store
            try {
                AppContext.addDepositToJsonFile(newDeposit);
                System.out.println("Deposit created successfully.");
            } catch (IOException e) {
                System.out.println("Error creating deposit: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("No account selected.");
        }
    }

    @FXML
    void onActionWithdraw(ActionEvent event) {

    }

}







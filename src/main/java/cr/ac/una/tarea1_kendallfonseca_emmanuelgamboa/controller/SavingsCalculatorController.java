package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Currency;
import java.util.ResourceBundle;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class SavingsCalculatorController extends Controller implements Initializable {

    @FXML
    private MFXButton btnAccept;

    @FXML
    private MFXButton btnDelete;

    @FXML
    private MFXFilterComboBox<String> comboBoxDeposits;

    @FXML
    private MFXFilterComboBox<String> comboBoxTime;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Savings> tableTypesSavings;

    @FXML
    private TableColumn<Savings, String> columnDeposit;

    @FXML
    private TableColumn<Savings, String> columnTime;

    @FXML
    private TableColumn<Savings, String> columnInterest;

    @FXML
    private TableColumn<Savings, String> columnTotal;

    private ObservableList<Savings> savingsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurar las opciones para el ComboBox de depósitos
        comboBoxDeposits.getItems().addAll("5.00₡", "10.00₡", "25.00₡", "50.00₡", "100.00₡", "500.00₡", "1,000.00₡", "2,000.00₡", "5,000.00₡", "10,000.00₡", "20,000.00₡");

        // Configurar las opciones para el ComboBox de tiempo
        comboBoxTime.getItems().addAll("1 mes.", "3 meses.", "6 meses.", "12 meses.");

        // Configurar la tabla
        columnDeposit.setCellValueFactory(new PropertyValueFactory<>("deposit"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        columnInterest.setCellValueFactory(new PropertyValueFactory<>("interest"));
        columnTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tableTypesSavings.setItems(savingsList);
    }

    @Override
    public void initialize() {
        // No se utiliza en este caso
    }

    @FXML
    void onActionBtnAccept(ActionEvent event) {
        String deposit = comboBoxDeposits.getSelectionModel().getSelectedItem();
        String time = comboBoxTime.getSelectionModel().getSelectedItem();

        // Reemplazar el separador decimal por un punto y eliminar el símbolo de moneda
        deposit = deposit.replace("₡", "").replace(",", "");

        // Parsear el valor del depósito utilizando NumberFormat
        NumberFormat format = NumberFormat.getNumberInstance(Locale.forLanguageTag("cr-CR"));
        Number number = null;
        try {
            number = format.parse(deposit);
        } catch (ParseException e) {
            // Manejar la excepción si el valor no se puede parsear
            System.out.println("Error al parsear el valor del depósito");
        }

        // Calcular el interés
        double interest = calculateInterest(Double.parseDouble(deposit), Integer.parseInt(time.substring(0, time.indexOf(" "))));

        // Calcular el total
        double total = Double.parseDouble(deposit) + interest;

        // Agregar el resultado a la lista
        savingsList.add(new Savings(deposit + "₡", time, String.format("%.2f", interest) + " ₡", String.format("%.2f", total) + " ₡"));
    }

    private double calculateInterest(double deposit, int time) {
        switch (time) {
            case 1:
                return deposit * 0.017;
            case 3:
                return deposit * 0.023;
            case 6:
                return deposit * 0.034;
            case 12:
                return deposit * 0.062;
            default:
                return 0;
        }
    }

    public static class Savings {
        private String deposit;
        private String time;
        private String interest;
        private String total;

        public Savings(String deposit, String time, String interest, String total) {
            this.deposit = deposit;
            this.time = time;
            this.interest = interest;
            this.total = total;
        }

        public String getDeposit() {
            return deposit;
        }

        public String getTime() {
            return time;
        }

        public String getInterest() {
            return interest;
        }

        public String getTotal() {
            return total;
        }
    }

    @FXML
    void onActionBtnDelete(ActionEvent event) {
        savingsList.clear();
    }
}
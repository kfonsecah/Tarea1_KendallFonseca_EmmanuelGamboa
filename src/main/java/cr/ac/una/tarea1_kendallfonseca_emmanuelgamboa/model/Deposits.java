package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;


public class Deposits {


    private int moneda;
    private int cantidad;
    private String folio;
    private String tipoCuenta;
    private String tipoMovimiento; // Nuevo atributo para el tipo de movimiento (retiro o depósito)
    private boolean inProcess;
    private Boolean selected;
    private LocalDateTime dateTime;




    public Deposits(int moneda, int cantidad, String folio, String tipoCuenta, boolean inProcess, String tipoMovimiento, boolean selected, LocalDateTime dateTime) {
        this.moneda = moneda;
        this.cantidad = cantidad;
        this.folio = folio;
        this.tipoCuenta = tipoCuenta;
        this.inProcess = true;
        this.tipoMovimiento = tipoMovimiento;
        this.selected = false;
        this.dateTime = LocalDateTime.now(); // Inicializar con la fecha y hora actual
    }

    public Deposits() {

    }


    public int getMoneda() {
        return moneda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public String getFolio() {
        return folio;
    }
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    // Nuevo método para obtener el tipo de movimiento
    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    // Nuevo método para establecer el tipo de movimiento
    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Boolean getSelected() {
        return selected;
    }
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
    public boolean isSelected() {

        if (selected != null) {

            return selected.booleanValue();
        } else {

            return false;
        }
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setInProcess(boolean inProcess) {
        this.inProcess = true;
    }
    public boolean isInProcess() {
        return inProcess;
    }
}

package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deposits {
    private String dateTimeString; // Campo para la fecha y hora como String

    private int moneda;
    private int cantidad;
    private String folio;
    private String tipoCuenta;
    private String tipoMovimiento;
    private boolean inProcess;
    private Boolean selected;
    public Deposits() {
    }

    public Deposits(int moneda, int cantidad, String folio, String tipoCuenta, boolean inProcess, String tipoMovimiento, boolean selected, String dateTimeString) {
        this.moneda = moneda;
        this.cantidad = cantidad;
        this.folio = folio;
        this.tipoCuenta = tipoCuenta;
        this.inProcess = true;
        this.tipoMovimiento = tipoMovimiento;
        this.selected = false;
        this.dateTimeString = dateTimeString; // Utilizar la cadena de fecha y hora proporcionada
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

    public String getDateTimeString() {
        return dateTimeString;
    }
    public void setDateTimeString(String dateTimeString) {
        this.dateTimeString = dateTimeString;
    }

    public void setInProcess(boolean inProcess) {
        this.inProcess = true;
    }

    public boolean isInProcess() {
        return inProcess;
    }
}

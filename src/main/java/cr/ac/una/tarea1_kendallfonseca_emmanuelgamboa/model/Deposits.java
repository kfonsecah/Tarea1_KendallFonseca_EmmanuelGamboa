package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;


public class Deposits {


    private int moneda;
    private int cantidad;
    private String folio;
    private String tipoCuenta;
    private String tipoMovimiento; // Nuevo atributo para el tipo de movimiento (retiro o depósito)
    private boolean inProcess;
    private Boolean selected;




    public Deposits(int moneda, int cantidad, String folio, String tipoCuenta, boolean inProcess, String tipoMovimiento, boolean selected) {
        this.moneda = moneda;
        this.cantidad = cantidad;
        this.folio = folio;
        this.tipoCuenta = tipoCuenta;
        this.inProcess = true;
        this.tipoMovimiento = tipoMovimiento;
        this.selected = false;
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
        // Check if the selected property is null
        if (selected != null) {
            // If not null, return its boolean value
            return selected.booleanValue();
        } else {
            // If null, return false or handle it in another appropriate way
            return false;
        }
    }

    public void setInProcess(boolean inProcess) {
        this.inProcess = true;
    }
    public boolean isInProcess() {
        return inProcess;
    }
}

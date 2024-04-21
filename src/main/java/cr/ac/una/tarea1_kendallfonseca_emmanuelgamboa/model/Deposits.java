package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
public class Deposits {

    private ObservableList<Deposits> depositosList;

    private final int moneda;
    private final IntegerProperty cantidad;
    private final StringProperty folio;
    private final StringProperty tipoCuenta;

    public Deposits(int moneda, int cantidad, String folio, String tipoCuenta) {
        this.moneda = moneda;
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.folio = new SimpleStringProperty(folio);
        this.tipoCuenta = new SimpleStringProperty(tipoCuenta);
    }

    public int getMoneda() {
        return moneda;
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }

    public String getFolio() {
        return folio.get();
    }

    public void setFolio(String folio) {
        this.folio.set(folio);
    }

    public StringProperty folioProperty() {
        return folio;
    }

    public String getTipoCuenta() {
        return tipoCuenta.get();
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta.set(tipoCuenta);
    }

    public StringProperty tipoCuentaProperty() {
        return tipoCuenta;
    }
}



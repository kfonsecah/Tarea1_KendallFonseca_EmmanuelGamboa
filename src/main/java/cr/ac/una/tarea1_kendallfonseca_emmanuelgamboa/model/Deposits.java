package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;


public class Deposits {

    private ObservableList<Deposits> depositosList;

    private final int moneda;
    private final IntegerProperty cantidad;

    public Deposits(int moneda, int cantidad) {
        this.moneda = moneda;
        this.cantidad = new SimpleIntegerProperty(cantidad);
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
}


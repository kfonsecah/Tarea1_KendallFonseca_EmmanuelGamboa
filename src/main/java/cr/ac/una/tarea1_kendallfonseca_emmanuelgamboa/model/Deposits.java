package cr.ac.una.tarea1_kendallfonseca_emmanuelgamboa.model;

import javafx.collections.ObservableList;


public class Deposits {


    private int moneda;
    private int cantidad;
    private String folio;
    private String tipoCuenta;




    public Deposits(int moneda, int cantidad, String folio, String tipoCuenta) {
        this.moneda = moneda;
        this.cantidad = cantidad;
        this.folio = folio;
        this.tipoCuenta = tipoCuenta;
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

}



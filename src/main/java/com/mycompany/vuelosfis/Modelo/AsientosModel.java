package com.mycompany.vuelosfis.Modelo;

import com.mycompany.vuelosfis.Util.Validador;

public class AsientosModel {

    public enum ClaseAsiento {
        PRIMERA,
        TURISTA
    }

    public enum EstadoAsiento {
        LIBRE,
        RESERVADO,
        COMPRADO
    }

    private String codigoVuelo;
    private String asiento;
    private ClaseAsiento clase;
    private EstadoAsiento estado;
    private String cedula;
    private double precio;

    public AsientosModel(String codigoVuelo,
                         String asiento,
                         ClaseAsiento clase,
                         EstadoAsiento estado,
                         String cedula,
                         double precio) {
        if (codigoVuelo == null || codigoVuelo.isBlank()) {
            throw new IllegalArgumentException("Codigo de vuelo invalido");
        }
        if (asiento == null || asiento.isBlank()) {
            throw new IllegalArgumentException("Asiento invalido");
        }
        if (clase == null) {
            throw new IllegalArgumentException("Clase invalida");
        }
        if (estado == null) {
            throw new IllegalArgumentException("Estado invalido");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("Precio invalido");
        }

        if (cedula != null && !cedula.isBlank() && !Validador.cedula(cedula)) {
            throw new IllegalArgumentException("Cedula invalida");
        }

        this.codigoVuelo = codigoVuelo;
        this.asiento = asiento;
        this.clase = clase;
        this.estado = estado;
        this.cedula = (cedula == null) ? "" : cedula;
        this.precio = precio;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public String getAsiento() {
        return asiento;
    }

    public ClaseAsiento getClase() {
        return clase;
    }

    public EstadoAsiento getEstado() {
        return estado;
    }

    public String getCedula() {
        return cedula;
    }

    public double getPrecio() {
        return precio;
    }

    public void setEstado(EstadoAsiento estado) {
        if (estado != null) {
            this.estado = estado;
        }
    }

    public void setCedula(String cedula) {
        if (cedula == null || cedula.isBlank()) {
            this.cedula = "";
            return;
        }
        if (!Validador.cedula(cedula)) {
            throw new IllegalArgumentException("Cedula invalida");
        }
        this.cedula = cedula;
    }

    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        }
    }
}

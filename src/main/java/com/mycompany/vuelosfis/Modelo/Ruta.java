package com.mycompany.vuelosfis.Modelo;

public class Ruta {

    private String origen;
    private String destino;

    public Ruta() {
    }

    public Ruta(String origen, String destino) {

        if (origen == null || origen.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar la ciudad de origen");
        }

        if (destino == null || destino.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar la ciudad de destino");
        }

        this.origen = origen.trim();
        this.destino = destino.trim();
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        if (origen != null && !origen.trim().isEmpty()) {
            this.origen = origen.trim();
        }
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        if (destino != null && !destino.trim().isEmpty()) {
            this.destino = destino.trim();
        }
    }

    @Override
    public String toString() {
        return origen + " -> " + destino;
    }
}
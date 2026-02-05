/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Modelo;

public class Reserva {

    private String codigoReserva;
    private Vuelo vuelo;
    private Pasajero pasajero;
    private EstadoReserva estado;
    private String fechaRegistro;
    private int cantidadAsientos;

    public Reserva() {
    }

    public Reserva(String codigoReserva, Vuelo vuelo,
                   Pasajero pasajero, EstadoReserva estado,
                   String fechaRegistro) {

        if (codigoReserva == null || codigoReserva.isEmpty()) {
            throw new IllegalArgumentException("Codigo de reserva invalido");
        }

        if (vuelo == null) {
            throw new IllegalArgumentException("Vuelo no puede ser nulo");
        }

        if (pasajero == null) {
            throw new IllegalArgumentException("Pasajero no puede ser nulo");
        }

        if (estado == null) {
            estado = EstadoReserva.RESERVADO;
        }

        this.codigoReserva = codigoReserva;
        this.vuelo = vuelo;
        this.pasajero = pasajero;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.cantidadAsientos = 1;
    }

    public Reserva(String codigoReserva, Vuelo vuelo,
                   Pasajero pasajero, EstadoReserva estado,
                   String fechaRegistro, int cantidadAsientos) {
        this(codigoReserva, vuelo, pasajero, estado, fechaRegistro);
        if (cantidadAsientos > 0) {
            this.cantidadAsientos = cantidadAsientos;
        }
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        if (codigoReserva != null && !codigoReserva.isEmpty()) {
            this.codigoReserva = codigoReserva;
        }
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        if (vuelo != null) {
            this.vuelo = vuelo;
        }
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        if (pasajero != null) {
            this.pasajero = pasajero;
        }
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        if (estado != null) {
            this.estado = estado;
        }
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        if (fechaRegistro != null && !fechaRegistro.isEmpty()) {
            this.fechaRegistro = fechaRegistro;
        }
    }

    public int getCantidadAsientos() {
        return cantidadAsientos;
    }

    public void setCantidadAsientos(int cantidadAsientos) {
        if (cantidadAsientos > 0) {
            this.cantidadAsientos = cantidadAsientos;
        }
    }

    @Override
    public String toString() {
        return codigoReserva +
               " | Vuelo: " + vuelo.getCodigo() +
               " | Pasajero: " + pasajero +
               " | Estado: " + estado +
               " | Fecha: " + fechaRegistro +
               " | Asientos: " + cantidadAsientos;
    }
}

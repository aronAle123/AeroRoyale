/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Modelo;

import com.mycompany.vuelosfis.Util.Validador;

public class Pasajero {

    private String cedula;
    private String nombreCompleto;
    private String correo;

    public Pasajero(String cedula, String nombreCompleto) {

        if (!Validador.cedula(cedula)) {
            throw new IllegalArgumentException("Cedula invalida");
        }

        if (!Validador.nombre(nombreCompleto)) {
            throw new IllegalArgumentException("Nombre invalido");
        }

        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
    }

    public Pasajero(String cedula, String nombreCompleto, String correo) {
        this(cedula, nombreCompleto);
        if (!Validador.correo(correo)) {
            throw new IllegalArgumentException("Correo invalido");
        }
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        if (Validador.cedula(cedula)) {
            this.cedula = cedula;
        }
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        if (Validador.nombre(nombreCompleto)) {
            this.nombreCompleto = nombreCompleto;
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (Validador.correo(correo)) {
            this.correo = correo;
        }
    }

    @Override
    public String toString() {
        return nombreCompleto + " (" + cedula + ")";
    }
}

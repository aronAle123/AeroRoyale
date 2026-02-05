/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Modelo;

/**
 *
 * @author caino
 */

public class Usuario {
    private final String usuario;
    private final String nombre;
    private final String correo;

    public Usuario(String usuario, String nombre, String correo) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }
}

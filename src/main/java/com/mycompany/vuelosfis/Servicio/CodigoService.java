/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Servicio;

public class CodigoService {
    private int contador = 1;

    public CodigoService() {}

    public String generarCodigoReserva() {
        String num = String.format("%06d", contador);
        contador++;
        return "RF-" + num;
    }
}

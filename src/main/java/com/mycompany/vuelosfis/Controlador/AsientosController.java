/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Controlador;

public class AsientosController {

    private final com.mycompany.vuelosfis.Servicio.AsientoService asientoService;

    public AsientosController(com.mycompany.vuelosfis.Servicio.AsientoService asientoService) {
        this.asientoService = asientoService;
    }

    public java.util.List<com.mycompany.vuelosfis.Modelo.AsientosModel> listarAsientos(String codigoVuelo) {
        return asientoService.listarPorVuelo(codigoVuelo);
    }

    public java.util.List<com.mycompany.vuelosfis.Modelo.AsientosModel> listarLibres(String codigoVuelo) {
        return asientoService.listarLibres(codigoVuelo);
    }

    public void reservar(String codigoVuelo, java.util.List<String> asientos, String cedula) {
        asientoService.reservar(codigoVuelo, asientos, cedula);
    }

    public void comprar(String codigoVuelo, java.util.List<String> asientos, String cedula) {
        asientoService.comprar(codigoVuelo, asientos, cedula);
    }

    public void liberar(String codigoVuelo, java.util.List<String> asientos) {
        asientoService.liberar(codigoVuelo, asientos);
    }
}

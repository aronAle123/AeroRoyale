/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Servicio;

public class AsientoService {

    public java.util.List<com.mycompany.vuelosfis.Modelo.AsientosModel> listarPorVuelo(String codigoVuelo) {
        return com.mycompany.vuelosfis.Util.AsientosCSVUtil.cargarAsientosPorVuelo(codigoVuelo);
    }

    public java.util.List<com.mycompany.vuelosfis.Modelo.AsientosModel> listarLibres(String codigoVuelo) {
        java.util.List<com.mycompany.vuelosfis.Modelo.AsientosModel> all = listarPorVuelo(codigoVuelo);
        java.util.List<com.mycompany.vuelosfis.Modelo.AsientosModel> libres = new java.util.ArrayList<>();
        for (com.mycompany.vuelosfis.Modelo.AsientosModel a : all) {
            if (a.getEstado() == com.mycompany.vuelosfis.Modelo.AsientosModel.EstadoAsiento.LIBRE) {
                libres.add(a);
            }
        }
        return libres;
    }

    public void reservar(String codigoVuelo, java.util.List<String> asientos, String cedula) {
        validarOperacion(codigoVuelo, asientos, cedula);
        validarDisponibles(codigoVuelo, asientos);
        com.mycompany.vuelosfis.Util.AsientosCSVUtil.actualizarAsientos(
                codigoVuelo,
                asientos,
                com.mycompany.vuelosfis.Modelo.AsientosModel.EstadoAsiento.RESERVADO,
                cedula
        );
    }

    public void comprar(String codigoVuelo, java.util.List<String> asientos, String cedula) {
        validarOperacion(codigoVuelo, asientos, cedula);
        validarDisponibles(codigoVuelo, asientos);
        com.mycompany.vuelosfis.Util.AsientosCSVUtil.actualizarAsientos(
                codigoVuelo,
                asientos,
                com.mycompany.vuelosfis.Modelo.AsientosModel.EstadoAsiento.COMPRADO,
                cedula
        );
    }

    public void liberar(String codigoVuelo, java.util.List<String> asientos) {
        if (codigoVuelo == null || codigoVuelo.isBlank()) {
            throw new IllegalArgumentException("Codigo de vuelo invalido");
        }
        if (asientos == null || asientos.isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar asientos");
        }
        com.mycompany.vuelosfis.Util.AsientosCSVUtil.actualizarAsientos(
                codigoVuelo,
                asientos,
                com.mycompany.vuelosfis.Modelo.AsientosModel.EstadoAsiento.LIBRE,
                ""
        );
    }

    private void validarOperacion(String codigoVuelo, java.util.List<String> asientos, String cedula) {
        if (codigoVuelo == null || codigoVuelo.isBlank()) {
            throw new IllegalArgumentException("Codigo de vuelo invalido");
        }
        if (asientos == null || asientos.isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar asientos");
        }
        if (!com.mycompany.vuelosfis.Util.Validador.cedula(cedula)) {
            throw new IllegalArgumentException("Cedula invalida");
        }
    }

    private void validarDisponibles(String codigoVuelo, java.util.List<String> asientos) {
        java.util.List<com.mycompany.vuelosfis.Modelo.AsientosModel> all = listarPorVuelo(codigoVuelo);
        java.util.Map<String, com.mycompany.vuelosfis.Modelo.AsientosModel> map = new java.util.HashMap<>();
        for (com.mycompany.vuelosfis.Modelo.AsientosModel a : all) {
            map.put(a.getAsiento(), a);
        }
        for (String asiento : asientos) {
            com.mycompany.vuelosfis.Modelo.AsientosModel a = map.get(asiento);
            if (a == null) {
                throw new IllegalArgumentException("Asiento no existe: " + asiento);
            }
            if (a.getEstado() != com.mycompany.vuelosfis.Modelo.AsientosModel.EstadoAsiento.LIBRE) {
                throw new IllegalArgumentException("Asiento ocupado: " + asiento);
            }
        }
    }
}

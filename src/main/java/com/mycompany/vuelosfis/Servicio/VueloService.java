/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Servicio;

import com.mycompany.vuelosfis.Modelo.Vuelo;
import com.mycompany.vuelosfis.csv.IVueloRepository;

import java.util.ArrayList;
import java.util.List;

public class VueloService {

    private final IVueloRepository vueloRepository;

    public VueloService(IVueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    public List<Vuelo> buscarVuelos(String origen, String destino, String fecha) {
        List<Vuelo> todos = vueloRepository.cargarVuelos();
        List<Vuelo> filtrados = new ArrayList<>();

        for (Vuelo v : todos) {
            boolean okOrigen = origen == null || origen.isBlank() || v.getRuta().getOrigen().equalsIgnoreCase(origen.trim());
            boolean okDestino = destino == null || destino.isBlank() || v.getRuta().getDestino().equalsIgnoreCase(destino.trim());
            boolean okFecha = fecha == null || fecha.isBlank() || v.getFecha().equalsIgnoreCase(fecha.trim());

            if (okOrigen && okDestino && okFecha) {
                filtrados.add(v);
            }
        }
        return filtrados;
    }

    public Vuelo buscarPorCodigo(String codigoVuelo) {
        if (codigoVuelo == null) return null;
        for (Vuelo v : vueloRepository.cargarVuelos()) {
            if (codigoVuelo.equalsIgnoreCase(v.getCodigo())) return v;
        }
        return null;
    }

    public void restarCupos(String codigoVuelo, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        Vuelo vuelo = buscarPorCodigo(codigoVuelo);
        if (vuelo == null) {
            throw new IllegalArgumentException("Vuelo no encontrado");
        }
        int disponibles = vuelo.getCuposDisponibles();
        if (cantidad > disponibles) {
            throw new IllegalArgumentException("No hay cupos suficientes");
        }
        int nuevoCupo = disponibles - cantidad;
        vueloRepository.actualizarCupos(codigoVuelo, nuevoCupo);
        vuelo.setCuposDisponibles(nuevoCupo);
    }
}

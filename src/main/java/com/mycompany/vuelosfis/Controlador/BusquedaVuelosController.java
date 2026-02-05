/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Controlador;

import com.mycompany.vuelosfis.Modelo.Vuelo;
import com.mycompany.vuelosfis.Servicio.VueloService;

import java.util.List;

public class BusquedaVuelosController {

    private final VueloService vueloService;

    public BusquedaVuelosController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    public List<Vuelo> buscar(String origen, String destino, String fecha) {
        return vueloService.buscarVuelos(origen, destino, fecha);
    }
    public Vuelo buscarPorCodigo(String codigo) {
        return vueloService.buscarPorCodigo(codigo);
    }
}

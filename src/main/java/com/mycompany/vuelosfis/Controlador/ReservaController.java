/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Controlador;

import com.mycompany.vuelosfis.Modelo.EstadoReserva;
import com.mycompany.vuelosfis.Modelo.Pasajero;
import com.mycompany.vuelosfis.Modelo.Reserva;
import com.mycompany.vuelosfis.Modelo.Vuelo;
import com.mycompany.vuelosfis.Servicio.ReservaService;

public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    public Reserva reservar(Vuelo vuelo, String cedula, String nombreCompleto, boolean comprar) {
        Pasajero p = new Pasajero(cedula, nombreCompleto);
        EstadoReserva estado = comprar ? EstadoReserva.COMPRADO : EstadoReserva.RESERVADO;
        return reservaService.crearReserva(vuelo, p, estado);
    }
}


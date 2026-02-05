/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Servicio;

import com.mycompany.vuelosfis.Modelo.EstadoReserva;
import com.mycompany.vuelosfis.Modelo.Pasajero;
import com.mycompany.vuelosfis.Modelo.Reserva;
import com.mycompany.vuelosfis.Modelo.Vuelo;
import com.mycompany.vuelosfis.csv.IReservaRepository;
import com.mycompany.vuelosfis.csv.IReservaRepository;
import com.mycompany.vuelosfis.Util.Validador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReservaService {

    private final IReservaRepository reservaRepository;
    private final VueloService vueloService; 
    private final CodigoService codigoService;

    public ReservaService(IReservaRepository reservaRepository, VueloService vueloService, CodigoService codigoService) {
        this.reservaRepository = reservaRepository;
        this.vueloService = vueloService;
        this.codigoService = codigoService;
    }

    public Reserva crearReserva(Vuelo vuelo, Pasajero pasajero, EstadoReserva estado) {
        if (vuelo == null) throw new IllegalArgumentException("Debe seleccionar un vuelo.");
        if (pasajero == null) throw new IllegalArgumentException("Debe ingresar pasajero.");

        if (!Validador.noVacio(pasajero.getCedula()) || !Validador.noVacio(pasajero.getNombreCompleto())) {
            throw new IllegalArgumentException("Cédula y nombre son obligatorios.");
        }

        if (vuelo.getCuposDisponibles() <= 0) {
            throw new IllegalArgumentException("No hay cupos disponibles.");
        }

        String codigo = codigoService.generarCodigoReserva();
        String fechaRegistro = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        Reserva r = new Reserva(codigo, vuelo, pasajero, estado, fechaRegistro);

        // Guardar en archivo
        reservaRepository.guardarReserva(r);

        // Nota: aquí no restamos cupos en archivo para no complicar (pueden hacerlo luego)
        return r;
    }

    public Reserva crearReserva(Vuelo vuelo, Pasajero pasajero, EstadoReserva estado, int cantidadAsientos) {
        if (cantidadAsientos <= 0) {
            throw new IllegalArgumentException("La cantidad de asientos debe ser mayor a cero.");
        }
        if (vuelo == null) throw new IllegalArgumentException("Debe seleccionar un vuelo.");
        if (pasajero == null) throw new IllegalArgumentException("Debe ingresar pasajero.");

        if (!Validador.noVacio(pasajero.getCedula()) || !Validador.noVacio(pasajero.getNombreCompleto())) {
            throw new IllegalArgumentException("Cédula y nombre son obligatorios.");
        }

        if (vuelo.getCuposDisponibles() < cantidadAsientos) {
            throw new IllegalArgumentException("No hay cupos disponibles.");
        }

        String codigo = codigoService.generarCodigoReserva();
        String fechaRegistro = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        Reserva r = new Reserva(codigo, vuelo, pasajero, estado, fechaRegistro, cantidadAsientos);

        reservaRepository.guardarReserva(r);
        vueloService.restarCupos(vuelo.getCodigo(), cantidadAsientos);
        return r;
    }

    public List<Reserva> listarReservas() {
        List<Reserva> reservas = reservaRepository.cargarReservas();

        for (Reserva r : reservas) {
            if (r.getVuelo() != null && r.getVuelo().getCodigo() != null) {
                Vuelo completo = vueloService.buscarPorCodigo(r.getVuelo().getCodigo());
                if (completo != null) r.setVuelo(completo);
            }
        }
        return reservas;
    }
}

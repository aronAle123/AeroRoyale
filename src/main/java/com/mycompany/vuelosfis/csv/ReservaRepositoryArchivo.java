/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.csv;

import com.mycompany.vuelosfis.Modelo.EstadoReserva;
import com.mycompany.vuelosfis.Modelo.Pasajero;
import com.mycompany.vuelosfis.Modelo.Reserva;
import com.mycompany.vuelosfis.Modelo.Vuelo;
import com.mycompany.vuelosfis.Util.CSVUtil;
import com.mycompany.vuelosfis.Util.DataPaths;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepositoryArchivo implements IReservaRepository {

    private final Path rutaArchivo;

    public ReservaRepositoryArchivo() {
        this.rutaArchivo = DataPaths.resolver("reservas.csv");
    }

    @Override
    public List<Reserva> cargarReservas() {
        List<Reserva> reservas = new ArrayList<>();

        if (!Files.exists(rutaArchivo)) {
            System.out.println("No existe: " + rutaArchivo.toAbsolutePath());
            return reservas;
        }

        try (BufferedReader br = Files.newBufferedReader(rutaArchivo)) {
            String linea;
            boolean primera = true;

            while ((linea = br.readLine()) != null) {
                if (primera) { primera = false; continue; }
                if (linea.trim().isEmpty()) continue;

                String[] p = CSVUtil.splitCSV(linea);
                if (p.length < 6) continue;

                String codigoReserva = p[0];
                String codigoVuelo = p[1];
                String cedula = p[2];
                String nombre = p[3];
                String correo = "";
                int cantidad = 1;
                EstadoReserva estado;
                String fechaRegistro;

                if (p.length >= 8) {
                    correo = p[4];
                    try {
                        cantidad = Integer.parseInt(p[5]);
                    } catch (NumberFormatException ex) {
                        cantidad = 1;
                    }
                    try {
                        estado = EstadoReserva.valueOf(p[6].trim());
                    } catch (Exception ex) {
                        continue;
                    }
                    fechaRegistro = p[7];
                } else {
                    try {
                        estado = EstadoReserva.valueOf(p[4].trim());
                    } catch (Exception ex) {
                        continue;
                    }
                    fechaRegistro = p[5];
                }

                try {
                    Vuelo vuelo = new Vuelo();
                    vuelo.setCodigo(codigoVuelo);

                    Pasajero pasajero;
                    if (correo == null || correo.isBlank() || !com.mycompany.vuelosfis.Util.Validador.correo(correo)) {
                        pasajero = new Pasajero(cedula, nombre);
                    } else {
                        pasajero = new Pasajero(cedula, nombre, correo);
                    }

                    Reserva r = new Reserva(codigoReserva, vuelo, pasajero, estado, fechaRegistro, cantidad);
                    reservas.add(r);
                } catch (RuntimeException ex) {
                    continue;
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo reservas.csv: " + e.getMessage());
        }

        return reservas;
    }

    @Override
    public void guardarReserva(Reserva r) {
        try {
            if (!Files.exists(rutaArchivo)) {
                Files.createDirectories(rutaArchivo.getParent());
                try (BufferedWriter bw = Files.newBufferedWriter(rutaArchivo, StandardOpenOption.CREATE)) {
                    bw.write("codigoReserva,codigoVuelo,cedula,nombreCompleto,correo,cantidadAsientos,estado,fechaRegistro");
                    bw.newLine();
                }
            }

            try (BufferedWriter bw = Files.newBufferedWriter(rutaArchivo, StandardOpenOption.APPEND)) {
                String linea = String.join(",",
                        CSVUtil.safe(r.getCodigoReserva()),
                        CSVUtil.safe(r.getVuelo().getCodigo()),
                        CSVUtil.safe(r.getPasajero().getCedula()),
                        CSVUtil.safe(r.getPasajero().getNombreCompleto()),
                        CSVUtil.safe(r.getPasajero().getCorreo()),
                        CSVUtil.safe(String.valueOf(r.getCantidadAsientos())),
                        CSVUtil.safe(r.getEstado().name()),
                        CSVUtil.safe(r.getFechaRegistro())
                );
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error guardando reserva: " + e.getMessage());
        }
    }
}

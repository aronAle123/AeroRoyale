/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.csv;

import com.mycompany.vuelosfis.Modelo.Avion;
import com.mycompany.vuelosfis.Modelo.Ruta;
import com.mycompany.vuelosfis.Modelo.Vuelo;
import com.mycompany.vuelosfis.Util.CSVUtil;
import com.mycompany.vuelosfis.Util.DataPaths;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class

VueloRepositoryArchivo implements IVueloRepository {

    private final Path rutaArchivo;

    public VueloRepositoryArchivo() {
         this.rutaArchivo = DataPaths.resolver("vuelos.csv");
    }

    @Override
    public List<Vuelo> cargarVuelos() {
        List<Vuelo> vuelos = new ArrayList<>();

        if (!Files.exists(rutaArchivo)) {
            System.out.println("No existe: " + rutaArchivo.toAbsolutePath());
            return vuelos;
        }

        try (BufferedReader br = Files.newBufferedReader(rutaArchivo)) {
            String linea;
            boolean primera = true;

            while ((linea = br.readLine()) != null) {
                if (primera) { primera = false; continue; } // encabezado
                if (linea.trim().isEmpty()) continue;

                String[] p = CSVUtil.splitCSV(linea);
                if (p.length < 10) continue;

                String codigo = p[0];
                String origen = p[1];
                String destino = p[2];
                String fecha = p[3];
                String hora = p[4];
                String avionCodigo = p[5];
                String avionModelo = p[6];
                int capacidad = Integer.parseInt(p[7]);
                int cupos = Integer.parseInt(p[8]);
                double precio = Double.parseDouble(p[9]);

                Ruta ruta = new Ruta(origen, destino);
                Avion avion = new Avion(avionCodigo, avionModelo, capacidad);

                Vuelo v = new Vuelo(codigo, ruta, avion, fecha, hora, precio, cupos);
                vuelos.add(v);
            }
        } catch (IOException | RuntimeException e) {
            System.out.println("Error leyendo vuelos.csv: " + e.getMessage());
        }

        return vuelos;
    }

    @Override
    public void actualizarCupos(String codigoVuelo, int nuevoCupo) {
        if (codigoVuelo == null || codigoVuelo.isBlank()) {
            return;
        }
        if (!Files.exists(rutaArchivo)) {
            System.out.println("No existe: " + rutaArchivo.toAbsolutePath());
            return;
        }

        List<String> lineas;
        try {
            lineas = Files.readAllLines(rutaArchivo, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error leyendo vuelos.csv: " + e.getMessage());
            return;
        }

        if (lineas.isEmpty()) {
            return;
        }

        List<String> actualizadas = new ArrayList<>(lineas.size());
        actualizadas.add(lineas.get(0));

        for (int i = 1; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            if (linea.trim().isEmpty()) {
                continue;
            }
            String[] p = CSVUtil.splitCSV(linea);
            if (p.length < 10) {
                actualizadas.add(linea);
                continue;
            }
            if (codigoVuelo.equalsIgnoreCase(p[0])) {
                p[8] = String.valueOf(nuevoCupo);
                String nuevaLinea = String.join(",",
                        CSVUtil.safe(p[0]),
                        CSVUtil.safe(p[1]),
                        CSVUtil.safe(p[2]),
                        CSVUtil.safe(p[3]),
                        CSVUtil.safe(p[4]),
                        CSVUtil.safe(p[5]),
                        CSVUtil.safe(p[6]),
                        CSVUtil.safe(p[7]),
                        CSVUtil.safe(p[8]),
                        CSVUtil.safe(p[9])
                );
                actualizadas.add(nuevaLinea);
            } else {
                actualizadas.add(linea);
            }
        }

        try {
            Files.write(rutaArchivo, actualizadas, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error actualizando vuelos.csv: " + e.getMessage());
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Util;

public class AsientosCSVUtil {

    private static final String ARCHIVO = "asientos.csv";

    public static java.util.List<com.mycompany.vuelosfis.Modelo.AsientosModel> cargarAsientos() {
        java.util.List<com.mycompany.vuelosfis.Modelo.AsientosModel> asientos = new java.util.ArrayList<>();
        java.nio.file.Path ruta = DataPaths.resolver(ARCHIVO);
        if (!java.nio.file.Files.exists(ruta)) {
            return asientos;
        }

        try (java.io.BufferedReader br = java.nio.file.Files.newBufferedReader(ruta)) {
            String linea;
            boolean primera = true;
            while ((linea = br.readLine()) != null) {
                if (primera) {
                    primera = false;
                    continue;
                }
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] p = CSVUtil.splitCSV(linea);
                if (p.length < 6) {
                    continue;
                }

                String codigoVuelo = p[0];
                String asiento = p[1];
                com.mycompany.vuelosfis.Modelo.AsientosModel.ClaseAsiento clase;
                com.mycompany.vuelosfis.Modelo.AsientosModel.EstadoAsiento estado;
                try {
                    clase = com.mycompany.vuelosfis.Modelo.AsientosModel.ClaseAsiento.valueOf(p[2]);
                    estado = com.mycompany.vuelosfis.Modelo.AsientosModel.EstadoAsiento.valueOf(p[3]);
                } catch (IllegalArgumentException ex) {
                    continue;
                }
                String cedula = p[4];
                double precio;
                try {
                    precio = Double.parseDouble(p[5]);
                } catch (NumberFormatException ex) {
                    precio = 0;
                }

                asientos.add(new com.mycompany.vuelosfis.Modelo.AsientosModel(
                        codigoVuelo,
                        asiento,
                        clase,
                        estado,
                        cedula,
                        precio
                ));
            }
        } catch (java.io.IOException ex) {
            System.out.println("Error leyendo asientos.csv: " + ex.getMessage());
        }

        return asientos;
    }

    public static java.util.List<com.mycompany.vuelosfis.Modelo.AsientosModel> cargarAsientosPorVuelo(String codigoVuelo) {
        java.util.List<com.mycompany.vuelosfis.Modelo.AsientosModel> all = cargarAsientos();
        java.util.List<com.mycompany.vuelosfis.Modelo.AsientosModel> res = new java.util.ArrayList<>();
        if (codigoVuelo == null || codigoVuelo.isBlank()) {
            return res;
        }
        for (com.mycompany.vuelosfis.Modelo.AsientosModel a : all) {
            if (codigoVuelo.equalsIgnoreCase(a.getCodigoVuelo())) {
                res.add(a);
            }
        }
        return res;
    }

    public static void guardarAsientos(java.util.List<com.mycompany.vuelosfis.Modelo.AsientosModel> asientos) {
        java.nio.file.Path ruta = DataPaths.resolver(ARCHIVO);
        try {
            java.nio.file.Files.createDirectories(ruta.getParent());
        } catch (java.io.IOException e) {
            System.out.println("Error creando directorios: " + e.getMessage());
        }

        try (java.io.BufferedWriter bw = java.nio.file.Files.newBufferedWriter(ruta)) {
            bw.write("codigoVuelo,asiento,clase,estado,cedula,precio");
            bw.newLine();
            for (com.mycompany.vuelosfis.Modelo.AsientosModel a : asientos) {
                String linea = String.join(",",
                        CSVUtil.safe(a.getCodigoVuelo()),
                        CSVUtil.safe(a.getAsiento()),
                        CSVUtil.safe(a.getClase().name()),
                        CSVUtil.safe(a.getEstado().name()),
                        CSVUtil.safe(a.getCedula()),
                        CSVUtil.safe(String.valueOf(a.getPrecio()))
                );
                bw.write(linea);
                bw.newLine();
            }
        } catch (java.io.IOException ex) {
            System.out.println("Error escribiendo asientos.csv: " + ex.getMessage());
        }
    }

    public static void actualizarAsientos(String codigoVuelo,
                                          java.util.List<String> asientos,
                                          com.mycompany.vuelosfis.Modelo.AsientosModel.EstadoAsiento estado,
                                          String cedula) {
        java.util.List<com.mycompany.vuelosfis.Modelo.AsientosModel> all = cargarAsientos();
        java.util.Set<String> set = new java.util.HashSet<>();
        if (asientos != null) {
            for (String s : asientos) {
                if (s != null && !s.isBlank()) {
                    set.add(s.trim());
                }
            }
        }

        for (com.mycompany.vuelosfis.Modelo.AsientosModel a : all) {
            if (!a.getCodigoVuelo().equalsIgnoreCase(codigoVuelo)) {
                continue;
            }
            if (!set.contains(a.getAsiento())) {
                continue;
            }
            a.setEstado(estado);
            if (estado == com.mycompany.vuelosfis.Modelo.AsientosModel.EstadoAsiento.LIBRE) {
                a.setCedula("");
            } else {
                a.setCedula(cedula);
            }
        }

        guardarAsientos(all);
    }
}

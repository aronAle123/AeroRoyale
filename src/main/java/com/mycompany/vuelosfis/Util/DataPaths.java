/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class DataPaths {

    private DataPaths() {
    }

    public static Path resolver(String nombreArchivo) {
        Path base = Paths.get("").toAbsolutePath();
        for (int i = 0; i < 4 && base != null; i++) {
            Path candidato = base.resolve("data").resolve(nombreArchivo);
            if (Files.exists(candidato)) {
                return candidato;
            }

            Path anidado = base.resolve("VuelosFIS").resolve("data").resolve(nombreArchivo);
            if (Files.exists(anidado)) {
                return anidado;
            }

            base = base.getParent();
        }

        return Paths.get("data", nombreArchivo);
    }
}

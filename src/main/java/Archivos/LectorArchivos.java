package Archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivos {

    public static List<String> leerLineas(String ruta) {
        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (Exception e) {
            System.out.println("Error leyendo archivo: " + ruta);
            e.printStackTrace();
        }

        return lineas;
    }
}
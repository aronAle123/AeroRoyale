/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Util;

public class CSVUtil {

    public static String[] splitCSV(String linea) {
        // Para este proyecto asumimos que NO habrá comas dentro de textos.
        String[] parts = linea.split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return parts;
    }

    public static String safe(String s) {
        if (s == null) return "";
        return s.replace(",", " "); // evita romper CSV
    }
}


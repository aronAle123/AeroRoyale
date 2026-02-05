/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vuelosfis.Util;

public class Validador {

    public static boolean noVacio(String s) {
        return s != null && !s.trim().isEmpty();
    }

    public static boolean fechaBasicaYYYYMMDD(String s) {
        // Validación súper simple (para principiantes)
        return noVacio(s) && s.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    public static boolean cedula(String s) {
        return noVacio(s) && s.matches("\\d{10}");
    }

    public static boolean correo(String s) {
        return noVacio(s) && s.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean nombre(String s) {
        return noVacio(s) && s.matches("^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ\\s]+$");
    }
}

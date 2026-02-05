/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.vuelosfis.csv;

import com.mycompany.vuelosfis.Modelo.Vuelo;
import java.util.List;

public interface IVueloRepository {
   
    public List<Vuelo> cargarVuelos();

    public void actualizarCupos(String codigoVuelo, int nuevoCupo);
}

package Persistencia;

import Archivos.LectorArchivos;
import com.mycompany.vuelosfis.Modelo.Vuelo;
import com.mycompany.vuelosfis.csv.IVueloRepository;
import java.util.ArrayList;
import java.util.List;

public class VueloRepoCSV implements IVueloRepository {

    private List<Vuelo> vuelos = new ArrayList<>();

    public VueloRepoCSV() {
        load();
    }

    private void load() {
        List<String> lineas = LectorArchivos.leerLineas("Datos/Vuelos.csv");

        for (String l : lineas) {
            String[] p = l.split(";");

            Vuelo v = new Vuelo(
                p[0],
                p[1],
                p[2],
                p[3],
                p[4],
                Double.parseDouble(p[5]),
                Integer.parseInt(p[6])
            );

            vuelos.add(v);
        }
    }

    @Override
    public List<Vuelo> cargarVuelos() {
        return vuelos;
    }

    @Override
    public void actualizarCupos(String codigoVuelo, int nuevoCupo) {
        if (codigoVuelo == null) {
            return;
        }
        for (Vuelo v : vuelos) {
            if (codigoVuelo.equalsIgnoreCase(v.getCodigo())) {
                v.setCuposDisponibles(nuevoCupo);
                break;
            }
        }
    }
}

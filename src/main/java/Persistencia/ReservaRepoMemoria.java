package persistencia;

import com.mycompany.vuelosfis.Modelo.Reserva;
import com.mycompany.vuelosfis.csv.IReservaRepository;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepoMemoria implements IReservaRepository {

    private List<Reserva> reservas = new ArrayList<>();

    @Override
    public void guardarReserva(Reserva r) {
        reservas.add(r);
    }

    @Override
    public List<Reserva> cargarReservas() {
        return reservas;
    }
}
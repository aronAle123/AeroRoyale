package com.mycompany.vuelosfis.Modelo;

public class Vuelo {

    private String codigo;
    private Ruta ruta;
    private Avion avion;
    private String fecha; // YYYY-MM-DD
    private String hora;  // HH:mm
    private double precio;
    private int cuposDisponibles;

    public Vuelo() {
    }

    public Vuelo(String codigo, Ruta ruta, Avion avion,
                 String fecha, String hora,
                 double precio, int cuposDisponibles) {

        validar(codigo, fecha, hora, precio, cuposDisponibles);

        this.codigo = codigo;
        this.ruta = ruta;
        this.avion = avion;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.cuposDisponibles = cuposDisponibles;
    }

    public Vuelo(String codigo, String origen, String destino,
                 String fecha, String hora,
                 double precio, int cuposDisponibles) {

        validar(codigo, fecha, hora, precio, cuposDisponibles);

        this.codigo = codigo;
        this.ruta = new Ruta(origen, destino);
        this.avion = null;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.cuposDisponibles = cuposDisponibles;
    }

    private void validar(String codigo, String fecha, String hora, double precio, int cuposDisponibles) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Codigo invalido");
        }
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException("Fecha invalida");
        }
        if (hora == null || hora.trim().isEmpty()) {
            throw new IllegalArgumentException("Hora invalida");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        if (cuposDisponibles < 0) {
            throw new IllegalArgumentException("Los cupos no pueden ser negativos");
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo != null && !codigo.trim().isEmpty()) {
            this.codigo = codigo;
        }
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        if (fecha != null && !fecha.trim().isEmpty()) {
            this.fecha = fecha;
        }
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        if (hora != null && !hora.trim().isEmpty()) {
            this.hora = hora;
        }
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio > 0) {
            this.precio = precio;
        }
    }

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(int cuposDisponibles) {
        if (cuposDisponibles >= 0) {
            this.cuposDisponibles = cuposDisponibles;
        }
    }

    @Override
    public String toString() {
        String rutaTxt = (ruta == null) ? "(sin ruta)" : ruta.toString();
        String avionTxt = (avion == null) ? "(sin avion)" : avion.toString();

        return "Vuelo " + codigo +
               " | " + rutaTxt +
               " | " + avionTxt +
               " | " + fecha + " " + hora +
               " | Precio: " + precio +
               " | Cupos: " + cuposDisponibles;
    }
}

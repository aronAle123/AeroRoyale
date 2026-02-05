package com.mycompany.vuelosfis.Controlador;
import com.mycompany.vuelosfis.Modelo.Usuario;

import com.mycompany.vuelosfis.csv.UsuarioRepositoryArchivo;

public class AuthController {
    private final UsuarioRepositoryArchivo usuarioRepo;

    public AuthController() {
        this.usuarioRepo = new UsuarioRepositoryArchivo();
    }

   
    public Usuario login(String usuario, String password) {

        if (usuario == null || usuario.isBlank()) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }

        
        Usuario usuarioValido = usuarioRepo.obtenerUsuario(usuario, password);
        if (usuarioValido == null) {
            throw new IllegalArgumentException("Usuario o contraseña incorrectos");
        }

        imprimirDatos(usuarioValido);
        return usuarioValido;
    }

    private void imprimirDatos(Usuario usuario) {
        System.out.println("=== Datos del usuario autenticado ===");
        System.out.println("Usuario: " + usuario.getUsuario());
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("Correo: " + usuario.getCorreo());
    
    }

    // =========================
    // REGISTRO
    // =========================
    public void registrar(String usuario,
                          String password,
                          String confirmarPassword,
                          String nombre,
                          String correo) {

        if (usuario == null || usuario.isBlank()) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }

        if (usuarioRepo.existeUsuario(usuario)) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }

        if (!password.equals(confirmarPassword)) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        if (!com.mycompany.vuelosfis.Util.Validador.nombre(nombre)) {
            throw new IllegalArgumentException("Nombre invalido");
        }

        if (!com.mycompany.vuelosfis.Util.Validador.correo(correo)) {
            throw new IllegalArgumentException("Correo invalido");
        }

        usuarioRepo.guardar(usuario, password, nombre, correo);
    }
}

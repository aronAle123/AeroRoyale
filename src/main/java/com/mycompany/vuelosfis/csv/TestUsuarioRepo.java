package com.mycompany.vuelosfis.csv;

public class TestUsuarioRepo {
    public static void main(String[] args) {

        UsuarioRepositoryArchivo repo = new UsuarioRepositoryArchivo();

        System.out.println("Login admin/1234: " + repo.validar("admin", "1234"));
        System.out.println("Login admin/0000: " + repo.validar("admin", "0000"));

        if (!repo.existeUsuario("juan")) {
            repo.guardar("juan", "abcd", "Juan Perez", "juan@mail.com");
            System.out.println("Usuario juan registrado");
        }

        System.out.println("Login juan/abcd: " + repo.validar("juan", "abcd"));
    }
}

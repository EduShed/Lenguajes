package com.example.reservas_gym;

public class Estudiante {
    String nombre;
    String apellido;
    String id;
    String pass;

    public Estudiante(String nombre, String apellido, String id, String pass)
    {
        this.nombre= nombre;;
        this.apellido = apellido;
        this.id = id;
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }
}

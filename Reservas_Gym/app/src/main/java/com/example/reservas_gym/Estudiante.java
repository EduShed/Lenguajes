package com.example.reservas_gym;

public class Estudiante {
    /*
     Atributos del Estudiante
      */
    String nombre;
    String apellido;
    String carrera;
    String id;
    String pass;

    /*
    Constructor
     */
    public Estudiante(String nombre, String apellido, String carrera, String id, String pass)
    {
        this.nombre= nombre;;
        this.apellido = apellido;
        this.carrera = carrera;
        this.id = id;
        this.pass = pass;
    }

    /*
    Encapsulamiento de los atributos
     */

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCarrera(){
        return carrera;
    }

    public String getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

}

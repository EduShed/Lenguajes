package com.example.reservas_gym;

public class Reserva {
    private String name;
    private String lastname;
    private String id;
    private String date;

    public Reserva(String name, String lastname, String id, String date){
        setName(name);
        setLastname(lastname);
        setId(id);
        setDate(date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

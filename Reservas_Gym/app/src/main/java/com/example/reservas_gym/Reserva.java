package com.example.reservas_gym;

public class Reserva {
    private String name;
    private String lastname;
    private String id;
    private String date;
    private String hour;

    public Reserva(String name, String lastname, String id, String date, String hour){
        setName(name);
        setLastname(lastname);
        setId(id);
        setDate(date);
        setHour(hour);
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

    public String getReservationInfo()
    {
        return (name + " " + lastname + " \n" + "Con ID: " + id + "\n" + "Fecha de reserva: " + date + "\n" + "Hora de reserva: " + hour);
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}

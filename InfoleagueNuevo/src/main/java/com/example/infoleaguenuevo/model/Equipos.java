package com.example.infoleaguenuevo.model;

public class Equipos {

    private String nombre;
    private int escudo;
    public Equipos(String nombre, int escudo) {
        this.nombre = nombre;
        this.escudo = escudo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEscudo() {
        return escudo;
    }
    public void setEscudo(int escudo) {
        this.escudo = escudo;
    }

}

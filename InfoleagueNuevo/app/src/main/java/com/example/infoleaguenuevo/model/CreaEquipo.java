package com.example.infoleaguenuevo.model;

public class CreaEquipo {

    private String uid;
    private String Nombre;
    private String ColorPrincipal;
    private String numJugadores;
    private String Puntos = "0";

    public CreaEquipo() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getColorPrincipal() {
        return ColorPrincipal;
    }

    public void setColorPrincipal(String colorPrincipal) {
        ColorPrincipal = colorPrincipal;
    }

    public String getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(String numJugadores) {
        this.numJugadores = numJugadores;
    }

    public String getPuntos() {
        return Puntos;
    }

    public void setPuntos(String puntos) {
        Puntos = puntos;
    }

    @Override
    public String toString() {
        return Nombre;
    }
}

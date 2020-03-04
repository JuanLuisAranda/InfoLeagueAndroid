package com.example.infoleaguenuevo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CreaEquipo implements Serializable {

    private String id;

    @Expose
    @SerializedName("Nombre")
    private String Nombre;

    @Expose
    @SerializedName("Color")
    private String ColorPrincipal;

    @Expose
    @SerializedName("Jugadores")
    private String numJugadores;

    @Expose
    @SerializedName("Puntos")
    private String Puntos;

    public CreaEquipo  (String nombre, String color, String num, String punt) {
        Nombre = nombre;
        ColorPrincipal = color;
        numJugadores = num;
        Puntos = punt;
    }

    public String getId() {
        return id;
    }

    public void setId(String uid) {
        this.id = uid;
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

package com.example.infoleaguenuevo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Usuario implements Serializable {
    @Expose
    @SerializedName("Uid")
    private String Uid ;

    @Expose
    @SerializedName("Nombre")
    private String nombre ;

    @Expose
    @SerializedName("Email")
    private String email ;

    public Usuario() { }
    public Usuario(String Uid, String nombre, String email) {
        this.Uid = Uid;
        this.nombre = nombre;
        this.email = email;
    }

    public String getUid() {
        return Uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

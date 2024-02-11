package com.example.goodcloset.modelos;

import com.google.gson.annotations.SerializedName;

public class ArmarioModelo {

    @SerializedName("id")
    private int id;

    @SerializedName("nombre")
    private String nombre_armario;

    @SerializedName("contador_de_outfits")
    private int contador_de_outfits;

    @SerializedName("contador_de_likes")
    private int contador_de_likes;

    @SerializedName("id_propietario")
    private Integer id_propietario;

    public void setId_propietario(Integer id_propietario) {
        this.id_propietario = id_propietario;
    }

    public int getId() {
        return id;
    }

    public String getNombre_armario() {
        return nombre_armario;
    }

    public void setNombre_armario(String nombre_armario) {
        this.nombre_armario = nombre_armario;
    }

    public int getContador_de_outfits() {
        return contador_de_outfits;
    }

    public int getContador_de_likes() {
        return contador_de_likes;
    }

    public Integer getId_propietario() {
        return id_propietario;
    }
}

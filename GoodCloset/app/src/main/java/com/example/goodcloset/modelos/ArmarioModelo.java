package com.example.goodcloset.modelos;

import com.google.gson.annotations.SerializedName;

public class ArmarioModelo {

    @SerializedName("id")
    private int id;

    @SerializedName("nombre_armario")
    private String nombre_armario;

    @SerializedName("contador_de_outfits")
    private int contador_de_outfits;

    @SerializedName("contador_de_likes")
    private int contador_de_likes;

    @SerializedName("id_propietario")
    private String id_propietario;

    public int getId() {
        return id;
    }

    public String getNombre_armario() {
        return nombre_armario;
    }

    public int getContador_de_outfits() {
        return contador_de_outfits;
    }

    public int getContador_de_likes() {
        return contador_de_likes;
    }

    public String getId_propietario() {
        return id_propietario;
    }
}

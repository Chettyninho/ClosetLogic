package com.example.goodcloset.modelos;

import com.google.gson.annotations.SerializedName;

public class PrendaModelo {

    @SerializedName("id")
    private int id;

    @SerializedName("fotoEnBase64")
    private String foto_prenda;

    public int getId() {
        return id;
    }

    public String getFoto_prenda() {
        return foto_prenda;
    }
    @Override
    public String toString() {
        return "PrendaModelo{" +
                "id=" + id +
                ", foto_prenda=" + foto_prenda +
                '}';
    }
}

package com.example.goodcloset.modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PrendaModelo implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("fotoEnBase64")
    private String fotoEnBase64;

    public int getId() {
        return id;
    }

    public String getFoto_prenda() {
        return fotoEnBase64;
    }
    @Override
    public String toString() {
        return "PrendaModelo{" +
                "id=" + id +
                ", foto_prenda=" + fotoEnBase64 +
                '}';
    }
}

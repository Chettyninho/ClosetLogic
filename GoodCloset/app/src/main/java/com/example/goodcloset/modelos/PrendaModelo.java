package com.example.goodcloset.modelos;

import com.google.gson.annotations.SerializedName;

public class PrendaModelo {

    @SerializedName("id")
    private int id;

    @SerializedName("marca")
    private String marca;

    @SerializedName("tipo")
    private String tipo;

    @SerializedName("talla")
    private String talla;

    @SerializedName("color")
    private String color;

    @SerializedName("enlace_web")
    private String enlace_web;

    @SerializedName("fotoEnBase64")
    private String foto_prenda;

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTalla() {
        return talla;
    }

    public String getColor() {
        return color;
    }

    public String getEnlace_web() {
        return enlace_web;
    }

    public String getFoto_prenda() {
        return foto_prenda;
    }
    @Override
    public String toString() {
        return "PrendaModelo{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", tipo='" + tipo + '\'' +
                ", talla='" + talla + '\'' +
                ", color='" + color + '\'' +
                ", enlace_web='" + enlace_web + '\'' +
                ", foto_prenda=" + foto_prenda +
                '}';
    }
}

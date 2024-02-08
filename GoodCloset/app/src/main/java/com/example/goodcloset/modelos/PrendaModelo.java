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

    @SerializedName("foto_prenda")
    private byte[] foto_prenda;

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

    public byte[] getFoto_prenda() {
        return foto_prenda;
    }
}

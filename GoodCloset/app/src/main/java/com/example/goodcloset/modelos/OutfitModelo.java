package com.example.goodcloset.modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OutfitModelo implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("fecha_creacion")
    private String fecha_creacion;
    @SerializedName("prendas")
    List<PrendaModelo> prendasDelOutfit;

    private List<String> fotoEnBase64; //contendra las img en base 64

    public OutfitModelo(String name, String description) {
        this.nombre = name;
        this.descripcion = description;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public List<PrendaModelo> getPrendasDelOutfit() {
        return prendasDelOutfit;
    }

    public List<String> getFotoEnBase64() {
        return fotoEnBase64;
    }

    public void setFotoEnBase64(List<String> fotoEnBase64) {
        this.fotoEnBase64 = fotoEnBase64;
    }
}

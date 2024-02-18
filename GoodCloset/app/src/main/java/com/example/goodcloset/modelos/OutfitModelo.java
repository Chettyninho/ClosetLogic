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

    private List<String> imgsBase64; //contendra las img en base 64

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

    public List<PrendaModelo> getPrendasDelOutfit(){
        return prendasDelOutfit;
    }
}

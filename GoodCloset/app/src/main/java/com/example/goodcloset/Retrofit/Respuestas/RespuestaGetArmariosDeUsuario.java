package com.example.goodcloset.Retrofit.Respuestas;

import com.example.goodcloset.modelos.ArmarioModelo;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RespuestaGetArmariosDeUsuario  implements Serializable {

    private List<ArmarioModelo> armarios;

    // Constructor, getters y setters

    public RespuestaGetArmariosDeUsuario(List<ArmarioModelo> armarios) {
        this.armarios = armarios;
    }

    public List<ArmarioModelo> getArmarios() {
        return armarios;
    }

    public void setArmarios(List<ArmarioModelo> armarios) {
        this.armarios = armarios;
    }
}

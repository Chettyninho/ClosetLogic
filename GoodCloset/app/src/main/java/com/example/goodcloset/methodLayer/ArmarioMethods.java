package com.example.goodcloset.methodLayer;

import android.util.Log;

import com.example.goodcloset.modelos.ArmarioModelo;

import java.util.ArrayList;
import java.util.List;

public class ArmarioMethods {
    public static List<String> getNombresArmarios(List<ArmarioModelo> armariosList) {
        List<String> nombresArmarios = new ArrayList<>();

        for (ArmarioModelo armarioModelo : armariosList) {
            Log.e("DEBUG: ArmarioMethods - getNombresArmarios(individual)", armarioModelo.getNombre_armario());
            nombresArmarios.add(armarioModelo.getNombre_armario());
        }

        Log.d("DEBUG: ArmarioMethods - getNombresArmarios", nombresArmarios.toString());

        return nombresArmarios;
    }

    public static Integer devolverIdArmario(String armarioSeleccionado, List<ArmarioModelo> armarioModeloList){
        Integer idArmario = 0;
        for (ArmarioModelo a : armarioModeloList) {
            if (a.getNombre_armario().equals(armarioSeleccionado)) {
                idArmario = a.getId();

            }
        }
        return idArmario;
    }
}

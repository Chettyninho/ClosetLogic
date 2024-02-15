package com.example.goodcloset.methodLayer;

import android.hardware.biometrics.BiometricManager;

import com.example.goodcloset.modelos.Usuario;

public class UsuarioMethods {
    public static String returnProfileImage(Usuario usuario){
        return usuario.getFotoUsuario();
    }
}

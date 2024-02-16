package com.example.goodcloset.methodLayer;

import com.example.goodcloset.modelos.UsuarioModelo;

public class UsuarioMethods {
    public static String returnProfileImage(UsuarioModelo usuarioModelo){
        return usuarioModelo.getFotoUsuario();
    }
}

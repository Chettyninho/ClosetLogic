package com.example.goodcloset.Retrofit;

import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;

public class SingletonUser {
    private static SingletonUser instancia;
    private RespuestaInsertarUsuario usuario;

    private SingletonUser() {
        // Constructor privado para evitar instanciación directa hay que dejarlo vacio
    }
    public static synchronized SingletonUser getInstance() {
        if (instancia == null) {
            instancia = new SingletonUser();
        }
        return instancia;
    }

    public RespuestaInsertarUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(RespuestaInsertarUsuario usuario) {
        this.usuario = usuario;
    }
}

package com.example.goodcloset.Retrofit;

import com.example.goodcloset.Entidades.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/usuarios/saveUser")
    Call<RespuestaInsertarUsuario> insertarUsuario(@Body Usuario usuario);
}


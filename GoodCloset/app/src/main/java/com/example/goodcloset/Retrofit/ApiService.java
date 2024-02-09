package com.example.goodcloset.Retrofit;

import com.example.goodcloset.Entidades.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/usuarios/saveUser")
    Call<RespuestaInsertarUsuario> insertarUsuario(@Body Usuario usuario);

    @GET("/usuarios/all")
    Call<List<Usuario>> obtenerUsuarios();

    @POST("/inicio/login")
        Call<RespuestaInsertarUsuario> login(@Body Usuario usuario);
}


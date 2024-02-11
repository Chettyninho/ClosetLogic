package com.example.goodcloset.Retrofit;

import com.example.goodcloset.Entidades.Usuario;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaGetArmariosDeUsuario;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("/usuarios/saveUser")
    Call<RespuestaInsertarUsuario> insertarUsuario(@Body Usuario usuario);

    @POST("/inicio/login")
    Call<RespuestaInsertarUsuario> login(@Body Usuario usuario);

    @GET("armario/propietario/{idPropietario}")
    Call<RespuestaGetArmariosDeUsuario> getArmariosUser(@Path("idPropietario") Integer idPropietario);

    @GET("/usuarios/seguidos/{idUsr}")
    Call<List<Usuario>> getUsersFollowedByMainUser(@Path("idUsr") Integer idPropietario);
    @GET("/usuarios/all")
    Call<List<Usuario>> obtenerUsuarios();
}


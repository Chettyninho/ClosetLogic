package com.example.goodcloset.Retrofit;

import com.example.goodcloset.modelos.Usuario;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.modelos.ArmarioModelo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("/inicio/login")
    Call<RespuestaInsertarUsuario> login(@Body Usuario usuario);

    @POST("/usuarios/saveUser")
    Call<RespuestaInsertarUsuario> insertarUsuario(@Body Usuario usuario);

    @GET("/usuarios/seguidos/{idUsr}")
    Call<List<Usuario>> getUsersFollowedByMainUser(@Path("idUsr") Integer idPropietario);
    @GET("/usuarios/all")
    Call<List<Usuario>> obtenerUsuarios();

    @GET("armario/propietario/{idPropietario}")
    Call<List<ArmarioModelo>> getArmariosUser(@Path("idPropietario") Integer idPropietario);

    @POST("armario/saveArmario")
    Call <ArmarioModelo> postArmariosUser(@Body ArmarioModelo armarioModelo);

}


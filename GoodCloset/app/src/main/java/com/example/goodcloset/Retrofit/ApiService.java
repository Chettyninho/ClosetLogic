package com.example.goodcloset.Retrofit;

import com.example.goodcloset.modelos.OutfitModelo;
import com.example.goodcloset.modelos.PrendaModelo;
import com.example.goodcloset.modelos.UsuarioModelo;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.modelos.ArmarioModelo;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("/inicio/login")
    Call<RespuestaInsertarUsuario> login(@Body UsuarioModelo usuarioModelo);

    @POST("/usuarios/saveUser")
    Call<RespuestaInsertarUsuario> insertarUsuario(@Body UsuarioModelo usuarioModelo);

    @GET("/usuarios/seguidos/{idUsr}")
    Call<List<UsuarioModelo>> getUsersFollowedByMainUser(@Path("idUsr") Integer idPropietario);
    @GET("/usuarios/all")
    Call<List<UsuarioModelo>> obtenerUsuarios();

    @GET("armario/propietario/{idPropietario}")
    Call<List<ArmarioModelo>> getArmariosUser(@Path("idPropietario") Integer idPropietario);

    @POST("armario/saveArmario")
    Call <ArmarioModelo> postArmariosUser(@Body ArmarioModelo armarioModelo);

    @POST("outfit/{idArmario}/newOutfit")
    Call<OutfitModelo> postOutfit(@Path("idArmario") Integer idArmario, @Body ArrayList<String> imagenesCapturadas);

    @GET("/find-user-by-username/{username}")
    Call<UsuarioModelo> getUserByUsername(@Path("id_usuario") String username);
    @GET("/usuarios/{id_usuario}")
    Call<RespuestaInsertarUsuario> getUsuarioById(@Path("id_usuario") int idUsuario);


    @GET("usuarios/fotos")
    Call<ResponseBody> obtenerImagen();

    @GET("/prenda/{idPrenda}")
    Call<PrendaModelo> getImgB64(@Path("idPrenda") Integer idPrenda);
    @POST("/usuarios/seguir/{id_seguido}/{id_seguidor}")
    Call<Void> follow4Follow(@Path("id_seguido") int idUsuarioExtraño, @Path("id_seguidor") int idUsuario);

    @GET("armario/{id_armario}/outfits")
    Call<List<OutfitModelo>> getOutfits_Armario(@Path("id_armario") Integer id_armario);

}


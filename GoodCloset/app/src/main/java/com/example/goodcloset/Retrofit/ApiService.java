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
import retrofit2.http.Query;

public interface ApiService {
    @POST("/inicio/login")
    Call<RespuestaInsertarUsuario> login(@Body UsuarioModelo usuarioModelo);

    @POST("/usuarios/saveUser")
    Call<RespuestaInsertarUsuario> insertarUsuario(@Body UsuarioModelo usuarioModelo);
    @POST("/usuarios/saveUserProfileImage")
    Call<RespuestaInsertarUsuario> insertarImagenAUsuario(@Body RespuestaInsertarUsuario usuariImageAdd);
     @GET("/usuarios/seguidos/{idUsr}")
    Call<List<UsuarioModelo>> getUsersFollowedByMainUser(@Path("idUsr") Integer idPropietario);
    @GET("/usuarios/all")
    Call<List<UsuarioModelo>> obtenerUsuarios();
    @GET("/find-user-by-username/{username}")
    Call<UsuarioModelo> getUserByUsername(@Path("id_usuario") String username);
    @GET("/usuarios/{id_usuario}")
    Call<RespuestaInsertarUsuario> getUsuarioById(@Path("id_usuario") int idUsuario);
    @POST("/usuarios/seguir/{id_seguido}/{id_seguidor}")
    Call<Void> follow4Follow(@Path("id_seguido") int idUsuarioExtraño, @Path("id_seguidor") int idUsuario);

    //yo creo que esta se puede quitar:
    @GET("usuarios/fotos")
    Call<ResponseBody> obtenerImagen();

    @POST("usuarios/editUser")
    Call<RespuestaInsertarUsuario> editUsr(@Body RespuestaInsertarUsuario usuarioModelo,@Query("contraseñaAntigua") String contraseñaAntigua, @Query("contraseñaNueva") String contraseñaNueva);

    @POST("usuarios/liketocloset/{id_usuario}/{id_armario}")
    Call<Void> likeToArmario (@Path("id_usuario")int id_usuario, @Path("id_armario") int id_armario);
    @GET("usuarios/likes/{id_usuario}")
    Call<List<ArmarioModelo>> getLikedCloset(@Path("id_usuario") Integer id_usuario);

    ///////////////////////////////////////////////////////////

    @GET("armario/{id_armario}/outfits")
    Call<List<OutfitModelo>> getOutfits_Armario(@Path("id_armario") Integer id_armario);
    @GET("armario/propietario/{idPropietario}")
    Call<List<ArmarioModelo>> getArmariosUser(@Path("idPropietario") Integer idPropietario);

    @POST("armario/saveArmario")
    Call <ArmarioModelo> postArmariosUser(@Body ArmarioModelo armarioModelo);

    ////////////////////////////////////////////////////////
    @POST("outfit/{idArmario}/newOutfit")
    Call<OutfitModelo> postOutfit(@Path("idArmario") Integer idArmario, @Body ArrayList<String> imagenesCapturadas, @Query("name") String name, @Query("description") String description);

    ///////////////////////////////////////////////////////////////
    @GET("/prenda/{idPrenda}")
    Call<PrendaModelo> getImgB64(@Path("idPrenda") Integer idPrenda);



}


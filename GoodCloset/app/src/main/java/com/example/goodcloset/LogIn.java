package com.example.goodcloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.goodcloset.Entidades.Usuario;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.Retrofit.RespuestaInsertarUsuario;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class LogIn extends AppCompatActivity {
    ImageView imgbg;

    EditText username,paswordsinHassear;
    Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        imgbg = findViewById(R.id.loginbg);
        imgbg.animate().translationY(BIND_ABOVE_CLIENT).setDuration(1000).setStartDelay(3000);

        username = findViewById(R.id.userLogin);
        paswordsinHassear = findViewById(R.id.pswdLogin);

        enviar = findViewById(R.id.enviarLogin);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepararInserccion();
            }
        });
    }
    private void prepararInserccion() {

        String userName = username.getText().toString();
        String contraseñaSinHassear = paswordsinHassear.getText().toString();
        //Log.d("contraseña: ","->" + contraseñaSinHassear);

        Usuario usuarioAInsertar = new Usuario(userName, contraseñaSinHassear);
        entrar(usuarioAInsertar);
    }

    private void entrar(Usuario usuarioAInsertar) {
        ApiService apiService = ApiClient.getInstance().getApiService();
        if (apiService != null) {
            Call<RespuestaInsertarUsuario> call = apiService.login(usuarioAInsertar);

            call.enqueue(new Callback<RespuestaInsertarUsuario>() {

                @Override
                public void onResponse(Call<RespuestaInsertarUsuario> call, Response<RespuestaInsertarUsuario> response) {

                    if (response.isSuccessful()) {
                        RespuestaInsertarUsuario respuesta = response.body();

                        //Log.d("salt"," : " + Arrays.toString(respuesta.getSalt());
                        //String userNameCheck = respuesta.getUserName(); // Obtén el usuario de la respuesta
                            Log.e("ALL RESPUESTA", "username" + respuesta.getUserName() +
                                    ", // pswdd: " + respuesta.getHashContraseña());

                        if (respuesta.getSalt() != null) {
                            Log.e("dentrodelIF:",": " + respuesta.getSalt());
                            respuesta.setSaltReal(Arrays.toString(respuesta.getSalt()).getBytes());
                            Log.d("salt", " : " + Arrays.toString(respuesta.getSalt()));
                            Intent i = new Intent(LogIn.this, MainActivity.class);
                            startActivity(i);
                            finish();

                            // Resto del código...
                        }else {
                           Log.d("surraza","SI BYTE ES NULL, ES DECIR NO COINCIDE EL USER.");

                            //Toast.makeText(LogIn.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                        }
                    }
                }

                @Override
                public void onFailure(Call<RespuestaInsertarUsuario> call, Throwable t) {
                    Log.e("Error en la llamada", "Tipo de excepción: " + t.getClass().getSimpleName());
                    if (t instanceof JsonSyntaxException) {
                        // Imprimir información adicional sobre el JSON en caso de JsonSyntaxException
                        Log.e("Error en la llamada", "Cuerpo de la respuesta JSON: " + ((JsonSyntaxException) t).getMessage());
                        // Imprimir información adicional sobre el JSON en caso de JsonSyntaxException
                        String jsonString = getJsonStringFromErrorBody(call, t);
                        Log.e("JSON", "Cuerpo de la respuesta JSON: " + jsonString);

                        if (jsonString != null) {
                            // Utilizar Gson para deserializar el JSON en un objeto RespuestaInsertarUsuario
                            Gson gson = new Gson();
                            RespuestaInsertarUsuario respuesta = gson.fromJson(jsonString, RespuestaInsertarUsuario.class);

                            // Imprimir cada dato del objeto RespuestaInsertarUsuario
                            Log.d("Datos del JSON", "ID: " + respuesta.getId());
                            Log.d("Datos del JSON", "Nombre: " + respuesta.getNombre());
                            Log.d("Datos del JSON", "Apellido: " + respuesta.getSurname());
                            // Añadir más líneas según los campos que tengas en la clase RespuestaInsertarUsuario
                        }
                    }
                }
            });
        } else {
            // Mostrar un Toast indicando que apiService es null, lo normal es que esto nucna sea null
            Toast.makeText(LogIn.this,"Error: apiService es null", Toast.LENGTH_SHORT).show();
        }

    }
    private String getJsonStringFromErrorBody(Call<RespuestaInsertarUsuario> call, Throwable t) {
        if (t instanceof HttpException) {
            ResponseBody errorBody = ((HttpException) t).response().errorBody();
            try {
                return errorBody != null ? errorBody.string() : null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
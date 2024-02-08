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

import retrofit2.Call;
import retrofit2.Callback;
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
        Log.d("contraseña: ","->" + contraseñaSinHassear);

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
                        if (respuesta != null) {
                            //String userNameCheck = respuesta.getUserName(); // Obtén el usuario de la respuesta

                            if (respuesta.getUserName() != null && respuesta.getEmail() != null && respuesta.getHashContraseña() != null) {
                                // Si el usuario tiene valores no nulos en email y contraseña, inicia HomeFragment
                                startActivity(new Intent(LogIn.this, HomeFragment.class));
                                finish();
                                Toast.makeText(LogIn.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                            } else {
                                // Si alguno de los campos es nulo, muestra un Toast de usuario no registrado
                                Toast.makeText(LogIn.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LogIn.this, "En el else del éxito", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LogIn.this, "En el else del éxito", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<RespuestaInsertarUsuario> call, Throwable t) {
                    Log.e("Respuesta Erronea", "Código de error: " + t.getMessage());
                }

            });
            // Toast.makeText(Registro.this,"INSERTYADO", Toast.LENGTH_SHORT).show();
        } else {
            // Mostrar un Toast indicando que apiService es null
            Toast.makeText(LogIn.this,"Error: apiService es null", Toast.LENGTH_SHORT).show();
        }

    }
}
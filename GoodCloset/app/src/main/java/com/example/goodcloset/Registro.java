package com.example.goodcloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.goodcloset.modelos.Usuario;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registro extends AppCompatActivity {

    ImageView imgbg;
    EditText txtView,name,surname,email,username,password;
    Button button;
    LinearLayout ly;

    private Usuario usuarioAInsertar;
    private ApiClient apiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        imgbg = findViewById(R.id.registrobg);
        txtView = findViewById(R.id.title);
        ly = findViewById(R.id.linearLayout);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.apellido);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        button = findViewById(R.id.enviar);

        imgbg.animate().translationY(-2100).setDuration(1000).setStartDelay(500);

        Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideleft);
        txtView.startAnimation(aniFade);

        Animation aniFade2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideright);
        name.startAnimation(aniFade2);

        Animation aniFade3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideleft);
        surname.startAnimation(aniFade3);

        Animation aniFade4 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideright);
        email.startAnimation(aniFade4);

        Animation aniFade5 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideleft);
        username.startAnimation(aniFade5);

        Animation aniFade6 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideright);
        password.startAnimation(aniFade6);

        Animation aniFade7 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        button.startAnimation(aniFade7);


        // Inicializar ApiClient con la URL base de tu API (?)
        apiClient = ApiClient.getInstance();

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                prepararYInsertarUsuario();
                startActivity(new Intent(new Intent(Registro.this, LogIn.class)));
            }

        });

    }

    private void prepararYInsertarUsuario() {
        //int idUsuario = Integer.parseInt(editTextid.getText().toString());
        String nombre = name.getText().toString();
        String aepllid = surname.getText().toString();
        String Email = email.getText().toString();
        String userName = username.getText().toString();
        String contraseñaSinHassear = password.getText().toString();

        usuarioAInsertar = new Usuario(nombre, aepllid, Email, userName, contraseñaSinHassear);
        Log.d("!!!!!!!!!!!!nombre de usuario: " + usuarioAInsertar.getUserName(),"contraseña: " +usuarioAInsertar.getContraseñaSinHassear());
        llamarRetrofitInsertarUsuario(usuarioAInsertar);
    }
    private void llamarRetrofitInsertarUsuario(Usuario usuarioAInsertar) {
        ApiService apiService = ApiClient.getInstance().getApiService();
        Log.e("NOMBRE:","VALOR:" + usuarioAInsertar.getNombre());
        if (apiService != null) {
            Call<RespuestaInsertarUsuario> call = apiService.insertarUsuario(usuarioAInsertar);
            String url = call.request().url().toString();

            Log.d("URL de la llamada", url);
            call.enqueue(new Callback<RespuestaInsertarUsuario>() {

                @Override
                public void onResponse(Call<RespuestaInsertarUsuario> call, Response<RespuestaInsertarUsuario> response) {
                    Log.e("antes del if de succesfull", "Código de error: " + response.code());

                    if (response.isSuccessful()) {
                        RespuestaInsertarUsuario respuesta = response.body();
                        Log.d("JSON de la respuesta", new Gson().toJson(respuesta));

                        Log.d("Respuesta Exitosa", String.valueOf(respuesta));
                        Toast.makeText(Registro.this, "INSERTYADO", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("Respuesta Erronea", "Código de error: " + response.code());
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
            Toast.makeText(Registro.this,"Error: apiService es null", Toast.LENGTH_SHORT).show();
        }

    }
}
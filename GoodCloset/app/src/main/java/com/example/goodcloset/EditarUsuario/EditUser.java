package com.example.goodcloset.EditarUsuario;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.goodcloset.R;
import com.example.goodcloset.methodLayer.ArmarioMethods;
import com.example.goodcloset.modelos.ArmarioModelo;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.Retrofit.SingletonUser;
import com.example.goodcloset.modelos.OutfitModelo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUser extends AppCompatActivity {
    RespuestaInsertarUsuario usuarioSingleton = SingletonUser.getInstance().getUsuario();
    EditText userName,nombre,email,contrasenaActual,ContrasenaNueva;
    Button enviarUsuarioModificado;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        userName = findViewById(R.id.username);
        userName.setHint("@ " + usuarioSingleton.getUserName());
        enviarUsuarioModificado = findViewById(R.id.sendModifyUser);
        nombre = findViewById(R.id.name);
        nombre.setHint(usuarioSingleton.getNombre());

        email = findViewById(R.id.emailDialog);
        email.setHint(usuarioSingleton.getEmail());
        //esto lo pasaremos y lo gestionaremos en service para ver si se cambian o no los datos,
        //si la contraseña actual es correcta se cambiaran. sino alerta de que contraseña incorrecta.
        contrasenaActual = findViewById(R.id.contrasenaActual);
        contrasenaActual.setHint("********");

        ContrasenaNueva = findViewById(R.id.password);

        enviarUsuarioModificado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String contraseñaAntigua = String.valueOf(contrasenaActual.getText());
                    String contraseñaNueva = String.valueOf(ContrasenaNueva.getText());
                if(contrasenaActual.getText()!=null && ContrasenaNueva.getText()!=null){
                    enviar(usuarioSingleton, contraseñaAntigua, contraseñaNueva);
                }
            }
        });
    }
    private void enviar(RespuestaInsertarUsuario usuario,String contraseñaAntigua,String contraseñaNueva){
        ApiService apiService = ApiClient.getInstance().getApiService();
        if(apiService!=null){

            if (userName != null && nombre != null && email != null &&
                    !TextUtils.isEmpty(userName.getText()) &&
                    !TextUtils.isEmpty(nombre.getText()) &&
                    !TextUtils.isEmpty(email.getText())) {
                usuario.setUserName(String.valueOf(userName.getText()));
                usuario.setNombre(String.valueOf(nombre.getText()));
                usuario.setEmail(String.valueOf(email.getText()));
            } else {
                // Mostrar un mensaje de error o realizar alguna acción si los campos están vacíos o nulos
                Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }


            Call<RespuestaInsertarUsuario> call = apiService.editUsr(usuario,contraseñaAntigua,contraseñaNueva);
Log.d("IN","IN");
            call.enqueue(new Callback<RespuestaInsertarUsuario>() {
                @Override
                public void onResponse(Call<RespuestaInsertarUsuario> call, Response<RespuestaInsertarUsuario> response) {
                    if (response.isSuccessful()) {
                        Log.d("Respuesta del servidor", response.body().toString());
                        usuarioSingleton = response.body();
                    } else {
                        Log.e("Error en la respuesta", response.message());
                    }
                }

                @Override
                public void onFailure(Call<RespuestaInsertarUsuario> call, Throwable t) {
                    Log.e("Error en la llamada", t.getMessage());
                }
            });
        }
    }



}
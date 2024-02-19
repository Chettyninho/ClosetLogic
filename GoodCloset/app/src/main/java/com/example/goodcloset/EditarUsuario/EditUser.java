package com.example.goodcloset.EditarUsuario;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
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
    EditText userName,nombre,email,contraseñaActual,ContraseñaNueva;
    Button enviarUsuarioModificado;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enviarUsuarioModificado = findViewById(R.id.sendModifyUser);
        setContentView(R.layout.activity_edit_user); // Asegúrate de que el nombre del layout sea correcto

        userName = findViewById(R.id.username);
        userName.setHint("@ " + usuarioSingleton.getUserName());

        nombre = findViewById(R.id.name);
        nombre.setHint(usuarioSingleton.getNombre());

        email = findViewById(R.id.emailDialog);
        email.setHint(usuarioSingleton.getEmail());
        //esto lo pasaremos y lo gestionaremos en service para ver si se cambian o no los datos,
        //si la contraseña actual es correcta se cambiaran. sino alerta de que contraseña incorrecta.
        contraseñaActual = findViewById(R.id.contraseñaActual);
        contraseñaActual.setHint("********");

        ContraseñaNueva = findViewById(R.id.password);

        enviarUsuarioModificado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar();
            }
        });
    }
private void enviar(){

}
    //falta la logica para hacer la peticion con la nueva contraseña, lo haremos pasando el id del usuario ya que lo tenemos.
    //creo que lo mejor seria meterlo en home controller

}
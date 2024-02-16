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
    RespuestaInsertarUsuario getUsuarioSingleton;
    EditText nombre,apellido,contraseñaActual,ContraseñaNueva;
    TextView userName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user); // Asegúrate de que el nombre del layout sea correcto

        // Recogemos los datos del usuario que ha iniciado sesión
        usuarioSingleton = SingletonUser.getInstance().getUsuario();

        userName = findViewById(R.id.username);

        userName.setText("@ " + usuarioSingleton.getUserName());
    }

    //falta la logica para hacer la peticion con la nueva contraseña, lo haremos pasando el id del usuario ya que lo tenemos.
    //creo que lo mejor seria meterlo en home controller

}
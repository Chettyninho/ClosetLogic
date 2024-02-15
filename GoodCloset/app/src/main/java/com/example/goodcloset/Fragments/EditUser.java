package com.example.goodcloset.Fragments;

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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class EditUser extends Fragment {
    RespuestaInsertarUsuario usuarioSingleton = SingletonUser.getInstance().getUsuario();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);


        return rootView;
    }


}
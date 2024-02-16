package com.example.goodcloset.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.goodcloset.R;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.Retrofit.SingletonUser;
import com.example.goodcloset.modelos.UsuarioModelo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    Button recoger;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
//rescatamos del singleton el id del usuario que ha iniciado sesion.
        RespuestaInsertarUsuario usuario = SingletonUser.getInstance().getUsuario();
        Integer idUsr = usuario.getId();

        recoger = rootView.findViewById(R.id.recoger);
        recoger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llamamos a la función para obtener los usuarios seguidos
                obtenerAllSeguidos(idUsr);

            }
        });
        // aqui voy a hacer el home, luego habra que cabiarlo, hago aqui el get all users
        // Devuelve la vista del fragmento
        return rootView;
    }

    public void obtenerAllSeguidos(Integer idUsr){
        //esta funcion se carga nada mas abrir la actividad, no se si deberia ser async(?)
        ApiService apiService = ApiClient.getInstance().getApiService();

        if (apiService!=null){
            Call<List<UsuarioModelo>> call = apiService.getUsersFollowedByMainUser(idUsr); // este 3 se sustituye por idUsr, lo que

            //pasa es que siempre entro con user = s que el id es 30 y nadie le sigue :(
            Log.d("TAG", "Llamada a la API iniciada");

            call.enqueue(new Callback<List<UsuarioModelo>>() {
                @Override
                public void onResponse(Call<List<UsuarioModelo>> call, Response<List<UsuarioModelo>> response) {
                    Log.d("TAG", "Código de respuesta: " + response.code());

                    if (response.isSuccessful()) {
                        List<UsuarioModelo> usuariosSeguidos = response.body();

                        Log.d(".u.", "" + response.body().toString());

                        if (!usuariosSeguidos.isEmpty()){
                            for (UsuarioModelo u : usuariosSeguidos) {
                                u.setSaltReal(Arrays.toString(u.getSalt()).getBytes());
                                Log.e("Id usuario del get", " : " + u.getId());
                            }
                        }else {
                            Log.e("....","LA LISTA ESTA VACIA");
                        }

                    } else {
                        try {
                            Log.e("Error en la respuesta", response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<UsuarioModelo>> call, Throwable t) {
                    Log.e("Error en la llamada", "Error: " + t.getMessage());
                }
            });

        }

    }


}

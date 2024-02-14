package com.example.goodcloset;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.goodcloset.PruebagetFoto.GetFotos;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;

import com.example.goodcloset.modelos.Usuario;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    private ImageView imageView;
    private ApiService apiService;
    private Button boton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        obtenerAllUsers();
        boton = rootView.findViewById(R.id.btnIrAGetFotos);

        // Configura el onClickListener para el botón
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Utiliza getActivity() para obtener el contexto de la actividad actual
                Intent intent = new Intent(getActivity(), GetFotos.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void obtenerAllUsers() {
        //de nuevo aqui tendremos que gestionar toda interaccion con cada usuario desde el onResponse, encapsular lo maximo posible el codigo.
        ApiService apiService = ApiClient.getInstance().getApiService();
        if (apiService!=null){
            Call<List<Usuario>> call = apiService.obtenerUsuarios(); // este 3 se sustituye por idUsr, lo que
            //pasa es que siempre entro con user = s que el id es 30 y nadie le sigue :(
            Log.d("TAG", "Llamada a la API iniciada");

            call.enqueue(new Callback<List<Usuario>>() {
                @Override
                public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                    Log.d("TAG", "Código de respuesta: " + response.code());

                    if (response.isSuccessful()) {
                        List<Usuario> allUsuarios = response.body();
                        if (!allUsuarios.isEmpty()){
                            for (Usuario u : allUsuarios) {

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
                public void onFailure(Call<List<Usuario>> call, Throwable t) {
                    Log.e("Error en la llamada", "Error: " + t.getMessage());
                }
            });

        }
    }



}
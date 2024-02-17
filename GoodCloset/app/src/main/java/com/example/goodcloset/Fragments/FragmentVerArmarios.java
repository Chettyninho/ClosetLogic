package com.example.goodcloset.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodcloset.Adapter.ProfileArmarioRVadapter;
import com.example.goodcloset.R;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.Retrofit.SingletonUser;
import com.example.goodcloset.modelos.ArmarioModelo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class FragmentVerArmarios extends Fragment {

    private ProfileArmarioRVadapter adapter;
    private ArrayList<ArmarioModelo> armariosList = new ArrayList<>();

    public List<ArmarioModelo> armarios;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ver_armarios, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewVerArmarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ProfileArmarioRVadapter(getContext(), armariosList);
        recyclerView.setAdapter(adapter);

        obtenerListaDeArmarios();

        return view;
    }

    //llamada a la api para obtener los amrarios del usuario
    private ArrayList<ArmarioModelo> obtenerListaDeArmarios() {

        //int idPropietario = usuario.getId();
        int idPropietario =1;
        // Obtener la instancia de ApiClient
        ApiClient apiClient = ApiClient.getInstance();

        // Obtener el servicio ApiInterface
        ApiService apiService = apiClient.getApiService();

        // Realizar la llamada a la API para obtener la lista de armarios
        Call<List<ArmarioModelo>> call = apiService.getArmariosUser(idPropietario);

        // Ejecutar la llamada asíncrona
        call.enqueue(new Callback<List<ArmarioModelo>>() {

            @Override
            public void onResponse(Call<List<ArmarioModelo>> call, Response<List<ArmarioModelo>> response) {
                if (response.isSuccessful()) {
                    // Obtener la lista de armarios del cuerpo de la respuesta
                    armarios = response.body();
                    // Imprimir el array de armarios en el registro
                    Log.d("Armarios", "Lista de armarios: " + armarios.toString());

                    // Actualizar el adaptador con la lista de armarios
                        adapter.actualizarLista(armarios);

                } else {

                    // Manejar la respuesta no exitosa
                    Log.e("Armarios", "Error al obtener la lista de armarios: " + response.message());

                }
            }

            @Override
            public void onFailure(Call<List<ArmarioModelo>> call, Throwable t) {
                // Manejar el fallo en la llamada
            }
        });
        ArrayList<ArmarioModelo> listaArmarios = new ArrayList<>();

        return listaArmarios;
    }
}
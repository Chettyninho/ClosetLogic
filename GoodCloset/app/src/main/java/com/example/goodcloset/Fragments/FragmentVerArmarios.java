package com.example.goodcloset.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodcloset.Adapter.ProfileArmarioRVadapter;
import com.example.goodcloset.R;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.Retrofit.SingletonUser;
import com.example.goodcloset.modelos.ArmarioModelo;
import com.example.goodcloset.modelos.UsuarioModelo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentVerArmarios extends Fragment {

    private ProfileArmarioRVadapter adapter;
    private ArrayList<ArmarioModelo> armariosList = new ArrayList<>();

    public List<ArmarioModelo> armarios;
    private RespuestaInsertarUsuario usuario = SingletonUser.getInstance().getUsuario();
    private UsuarioModelo usuarioRecibido;

    public FragmentVerArmarios(UsuarioModelo usuarioRecibido) {
    this.usuarioRecibido = usuarioRecibido;
    }

    public FragmentVerArmarios() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ver_armarios, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewVerArmarios);
        ImageView imageViewEmpty = view.findViewById(R.id.imageViewEmpty);

        // Configurar un GridLayoutManager con 3 columnas
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ProfileArmarioRVadapter(getContext(), armariosList);
        recyclerView.setAdapter(adapter);

        Log.d("singleton","" + usuario);
        Log.d("usuarioRecibdo","" + usuarioRecibido);

        if(usuarioRecibido == null){
            obtenerListaDeArmarios(recyclerView,imageViewEmpty);

        }else{
            obtenerListaDeArmariosExtrangeProfile(usuarioRecibido,recyclerView,imageViewEmpty);

        }

        return view;
    }

    // Llamada a la API para obtener los armarios del usuario
    private void obtenerListaDeArmarios(RecyclerView recyclerView,ImageView imageViewEmpty) {
        int idPropietario = usuario.getId();

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

                    // Verificar si la lista está vacía para mostrar la imagen
                    if (armarios.isEmpty()) {
                        recyclerView.setVisibility(View.GONE); // Oculta el RecyclerView
                        imageViewEmpty.setVisibility(View.VISIBLE); // Muestra la imagen
                    } else {
                        recyclerView.setVisibility(View.VISIBLE); // Muestra el RecyclerView
                        imageViewEmpty.setVisibility(View.GONE); // Oculta la imagen
                    }
                } else {
                    // Manejar la respuesta no exitosa
                    Log.e("Armarios", "Error al obtener la lista de armarios: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ArmarioModelo>> call, Throwable t) {
                // Manejar el fallo en la llamada
                Log.e("Armarios", "Error al obtener la lista de armarios", t);
            }
        });
    }
    private void obtenerListaDeArmariosExtrangeProfile(UsuarioModelo usuarioRecibido,RecyclerView recyclerView,ImageView imageViewEmpty) {
        int idPropietario = usuarioRecibido.getId();

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

                    // Verificar si la lista está vacía para mostrar la imagen
                    if (armarios.isEmpty()) {
                        recyclerView.setVisibility(View.GONE); // Oculta el RecyclerView
                        imageViewEmpty.setVisibility(View.VISIBLE); // Muestra la imagen
                    } else {
                        recyclerView.setVisibility(View.VISIBLE); // Muestra el RecyclerView
                        imageViewEmpty.setVisibility(View.GONE); // Oculta la imagen
                    }
                } else {
                    // Manejar la respuesta no exitosa
                    Log.e("Armarios", "Error al obtener la lista de armarios: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ArmarioModelo>> call, Throwable t) {
                // Manejar el fallo en la llamada
                Log.e("Armarios", "Error al obtener la lista de armarios", t);
            }
        });
    }
}

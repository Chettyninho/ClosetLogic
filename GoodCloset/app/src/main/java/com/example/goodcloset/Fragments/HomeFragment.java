package com.example.goodcloset.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.goodcloset.Adapter.HomeRVAdapter;
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

public class HomeFragment extends Fragment {

    List<UsuarioModelo> allUsuarioModelos;
    private RecyclerView recyclerView;

    private ArrayList<UsuarioModelo> itemList = new ArrayList<>();

    private HomeRVAdapter adapter;
    Button recoger;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
//rescatamos del singleton el id del usuario que ha iniciado sesion.
        RespuestaInsertarUsuario usuario = SingletonUser.getInstance().getUsuario();
        Integer idUsr = usuario.getId();


        recyclerView = rootView.findViewById(R.id.recyclerView);
        itemList = new ArrayList<>();

        adapter = new HomeRVAdapter(getContext(), itemList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiClient apiClient = ApiClient.getInstance();

        // Obtener el servicio ApiInterface
        ApiService apiService = apiClient.getApiService();

        // Realizar la llamada a la API para obtener la lista de armarios
        Call<List<UsuarioModelo>> call = apiService.getUsersFollowedByMainUser(usuario.getId());

        // Ejecutar la llamada asíncrona
        call.enqueue(new Callback<List<UsuarioModelo>>() {

            @Override
            public void onResponse(Call<List<UsuarioModelo>> call, Response<List<UsuarioModelo>> response) {

                if (response.isSuccessful()) {
                    // Obtener la lista de armarios del cuerpo de la respuesta
                    allUsuarioModelos = response.body();
                    // Imprimir el array de armarios en el registro
                    Log.d("Usuarios", "Lista de Usuarios: " + allUsuarioModelos.toString());

                    // Actualizar el adaptador con la lista de armarios
                    adapter.actualizarLista(allUsuarioModelos);
                }
            }

            @Override
            public void onFailure(Call<List<UsuarioModelo>> call, Throwable t) {

            }

        });

        ArrayList<ArmarioModelo> listaArmarios = new ArrayList<>();

        return rootView;
    }


}

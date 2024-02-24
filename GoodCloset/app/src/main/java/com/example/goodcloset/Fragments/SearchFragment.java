package com.example.goodcloset.Fragments;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodcloset.Adapter.HomeRVAdapter;
import com.example.goodcloset.Adapter.LupaAdapter;
import com.example.goodcloset.Adapter.SearchRVAdapter;
import com.example.goodcloset.ExampleItem;
import com.example.goodcloset.PruebagetFoto.GetFotos;
import com.example.goodcloset.R;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;

import com.example.goodcloset.modelos.ArmarioModelo;
import com.example.goodcloset.modelos.UsuarioModelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    private ImageView imageView;
    private ApiService apiService;
    private Button boton;
    List<UsuarioModelo> allUsuarioModelos;
    private RecyclerView recyclerView, recyclerViewFotos;
    private ArrayList<UsuarioModelo> itemList = new ArrayList<>();
    private ArrayList<UsuarioModelo> fotoUsuarios = new ArrayList<>();
    private LupaAdapter itemAdapter;
    private SearchRVAdapter adapter;
    private SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        obtenerAllUsers();
        boton = rootView.findViewById(R.id.btnIrAGetFotos);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        searchView = rootView.findViewById(R.id.searchView);
        searchView.clearFocus();
        itemList = new ArrayList<>();

        ApiClient apiClient = ApiClient.getInstance();

        // Obtener el servicio ApiInterface
        ApiService apiService = apiClient.getApiService();

        // Realizar la llamada a la API para obtener la lista de armarios
        Call<List<UsuarioModelo>> call = apiService.obtenerUsuarios();

        itemAdapter = new LupaAdapter(getContext(), itemList);
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
                    itemAdapter.actualizarLista(allUsuarioModelos);

                }
            }

            @Override
            public void onFailure(Call<List<UsuarioModelo>> call, Throwable t) {

            }

        });

        ArrayList<UsuarioModelo> listaArmarios = new ArrayList<>();
        itemAdapter = new LupaAdapter(getContext(), itemList);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        recyclerView.setVisibility(View.GONE);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setIconified(false);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerViewFotos.setVisibility(View.GONE);

            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                recyclerView.setVisibility(View.GONE);
                recyclerViewFotos.setVisibility(View.VISIBLE);

                return false;
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        // Configura el onClickListener para el botón
       boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Utiliza getActivity() para obtener el contexto de la actividad actual
                Intent intent = new Intent(getActivity(), GetFotos.class);
                startActivity(intent);
            }
        });



        recyclerViewFotos = rootView.findViewById(R.id.RecyclerVistaTodasLasFotos);
        fotoUsuarios = new ArrayList<>();

        adapter = new SearchRVAdapter(getContext(), fotoUsuarios);
        recyclerViewFotos.setAdapter(adapter);
        recyclerViewFotos.setLayoutManager(new LinearLayoutManager(getContext()));

        Call<List<UsuarioModelo>> call1 = apiService.obtenerUsuarios();

        // Ejecutar la llamada asíncrona
        call1.enqueue(new Callback<List<UsuarioModelo>>() {

            @Override
            public void onResponse(Call<List<UsuarioModelo>> call1, Response<List<UsuarioModelo>> response) {

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

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewFotos.setLayoutManager(layoutManager);

        return rootView;

    }




    private void filterList(String text) {
        ArrayList<UsuarioModelo> filterList = new ArrayList<>();
        for (UsuarioModelo item : itemList){
            if(item.getUserName().toLowerCase().contains((text.toLowerCase()))){
                filterList.add(item);
            }
        }

        if(filterList.isEmpty()){
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
        } else {

            itemAdapter.setFilteredLsit(filterList);

        }
    }
   private void obtenerAllUsers() {
        //de nuevo aqui tendremos que gestionar toda interaccion con cada usuario desde el onResponse, encapsular lo maximo posible el codigo.
        ApiService apiService = ApiClient.getInstance().getApiService();
        if (apiService!=null){
            Call<List<UsuarioModelo>> call = apiService.obtenerUsuarios(); // este 3 se sustituye por idUsr, lo que
            //pasa es que siempre entro con user = s que el id es 30 y nadie le sigue :(
            Log.d("TAG", "Llamada a la API iniciada");

            call.enqueue(new Callback<List<UsuarioModelo>>() {
                @Override
                public void onResponse(Call<List<UsuarioModelo>> call, Response<List<UsuarioModelo>> response) {
                    Log.d("TAG", "Código de respuesta: " + response.code());

                    if (response.isSuccessful()) {
                        allUsuarioModelos = response.body();
                        if (!allUsuarioModelos.isEmpty()){
                            for (UsuarioModelo u : allUsuarioModelos) {

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
package com.example.goodcloset.Fragments;

import android.content.Intent;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodcloset.Adapter.ExampleAdapter;
import com.example.goodcloset.ExampleItem;
import com.example.goodcloset.PruebagetFoto.GetFotos;
import com.example.goodcloset.R;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;

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
    private RecyclerView recyclerView;

    private ArrayList<ExampleItem> itemList = new ArrayList<>();

    private ExampleAdapter itemAdapter;

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



        itemList.add(new ExampleItem(ContextCompat.getDrawable(getContext(),R.drawable.baseline_person_24), "Mario", "Ten"));
        itemList.add(new ExampleItem(ContextCompat.getDrawable(getContext(),R.drawable.baseline_person_24), "Miguel", "Eleven"));
        itemList.add(new ExampleItem(ContextCompat.getDrawable(getContext(),R.drawable.baseline_person_24), "Carlos", "Twelve"));
        itemList.add(new ExampleItem(ContextCompat.getDrawable(getContext(),R.drawable.baseline_person_24), "Ruben", "Ten"));
        itemList.add(new ExampleItem(ContextCompat.getDrawable(getContext(),R.drawable.baseline_person_24), "Paquito", "Eleven"));
        itemList.add(new ExampleItem(ContextCompat.getDrawable(getContext(),R.drawable.baseline_person_24), "Paloma", "Twelve"));
        itemList.add(new ExampleItem(ContextCompat.getDrawable(getContext(),R.drawable.baseline_person_24), "Carmen", "Ten"));
        itemList.add(new ExampleItem(ContextCompat.getDrawable(getContext(),R.drawable.baseline_person_24), "Rudiguer", "Eleven"));
        itemList.add(new ExampleItem(ContextCompat.getDrawable(getContext(),R.drawable.baseline_person_24), "Maria", "Twelve"));

        itemAdapter = new ExampleAdapter(getContext(), itemList);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        recyclerView.setVisibility(View.GONE);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setIconified(false);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                recyclerView.setVisibility(View.GONE);
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

        return rootView;
    }

    private void filterList(String text) {
        ArrayList<ExampleItem> filterList = new ArrayList<>();
        for (ExampleItem item : itemList){
            if(item.getText1().toLowerCase().contains((text.toLowerCase()))){
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
package com.example.goodcloset;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.goodcloset.Adapter.CustomPagerAdapter;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.modelos.ArmarioModelo;
import com.example.goodcloset.modelos.OutfitModelo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarrouselArmario extends AppCompatActivity  {
    private List<Integer> imageList = new ArrayList<>();
    //esto debera ser una lista

    //armario que recibe cuando tocas en alguno del perfil
    ArmarioModelo armarioRecibido = new ArmarioModelo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_outfits_armarios);

        ViewPager viewPager = findViewById(R.id.viewPager);
        Button button = findViewById(R.id.button3);

        // Recibir el ID del armario del intent
        armarioRecibido = (ArmarioModelo) getIntent().getSerializableExtra("armario");

        // Mostrar un Toast con el ID del armario
        Toast.makeText(this, "nombre del ararmio " + armarioRecibido.getNombre_armario(), Toast.LENGTH_SHORT).show();
        //si sale -1 significa q no pilla bien el id
        Integer idArmario = armarioRecibido.getId();
        obtenerOutfits(imageList,armarioRecibido);

        //no se si imageList seria lo bueno,
        //creo que obtenerOutfits() tendra que devolver una lista
        //de tipoOutfitModel que contiene unalista de prendas
        //esa lista de eOutfitModel creo que es la que deberia pasarse al
        //adaptador y desde ahi seleccionar las imagenes para mostrar.
        CustomPagerAdapter adapter = new CustomPagerAdapter(this, imageList);
        viewPager.setAdapter(adapter);
    }

    private void obtenerOutfits(List<Integer> imageList, ArmarioModelo armarioRecibido){

        ApiService apiService = ApiClient.getInstance().getApiService();
        if (apiService!= null){
            Call<List<OutfitModelo>> call = apiService.getOutfits_Armario(armarioRecibido.getId());
        call.enqueue(new Callback<List<OutfitModelo>>() {
            @Override
            public void onResponse(Call<List<OutfitModelo>> call, Response<List<OutfitModelo>> response) {
                if (response.isSuccessful()){
                    List<OutfitModelo> listaOutfits =response.body();
                    Log.d("responseBody Miguel Test", listaOutfits.toString());

                }
            }

            @Override
            public void onFailure(Call<List<OutfitModelo>> call, Throwable t) {

            }
        });
        }
    }
}

/*
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.goodcloset.Adapter.CustomPagerAdapter;
import com.example.goodcloset.Retrofit.ApiClient;


import java.util.ArrayList;
import java.util.List;

public class CarrouselArmario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_outfits_armarios);

        ViewPager viewPager = findViewById(R.id.viewPager);
        Button button = findViewById(R.id.button3);

        // Suponiendo que tienes un método en tu API para obtener la lista de imágenes
        List<YourImageModel> imageListFromAPI = ApiClient.fetchImageListFromAPI("URL_DE_TU_API");

        CustomPagerAdapter adapter = new CustomPagerAdapter(this, imageListFromAPI);
        viewPager.setAdapter(adapter);
    }

    private List<YourImageModel> tuMetodoParaObtenerListaDeImagenesDesdeAPI() {
        // Aquí deberías hacer la llamada a tu API para obtener la lista de imágenes
        // Por ahora, simplemente creamos una lista de imágenes de ejemplo
        List<YourImageModel> imageList = new ArrayList<>();

        // Añade aquí tus imágenes obtenidas desde la API

        return imageList;
    }
}
*/


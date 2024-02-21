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
    private List<OutfitModelo> outfitsDeArmario = new ArrayList<>();
    //esto debera ser una lista

    //armario que recibe cuando tocas en alguno del perfil
    ArmarioModelo armarioRecibido = new ArmarioModelo();
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_outfits_armarios);

        viewPager = findViewById(R.id.carrouselDeOutfitsViewPager); // Inicializa viewPager aquí

        Button button = findViewById(R.id.button3);

        // Recibir el ID del armario del intent
        armarioRecibido = (ArmarioModelo) getIntent().getSerializableExtra("armario");

        // Mostrar un Toast con el ID del armario
        Toast.makeText(this, "nombre del ararmio " + armarioRecibido.getNombre_armario(), Toast.LENGTH_SHORT).show();
        obtenerOutfits(armarioRecibido);

    }

    private void obtenerOutfits(ArmarioModelo armarioRecibido){
        ApiService apiService = ApiClient.getInstance().getApiService();
        if (apiService!= null){
            Call<List<OutfitModelo>> call = apiService.getOutfits_Armario(armarioRecibido.getId());
            call.enqueue(new Callback<List<OutfitModelo>>() {
                @Override
                public void onResponse(Call<List<OutfitModelo>> call, Response<List<OutfitModelo>> response) {
                    if (response.isSuccessful()){
                        outfitsDeArmario =response.body();
                        if (outfitsDeArmario==null){

                        }else{
                            CustomPagerAdapter adapter = new CustomPagerAdapter(CarrouselArmario.this, outfitsDeArmario);
                            viewPager.setAdapter(adapter);
                            }
                        }
                    }
                @Override
                public void onFailure(Call<List<OutfitModelo>> call, Throwable t) {

                }
            });
        }

    }
}


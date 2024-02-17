package com.example.goodcloset;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.goodcloset.Adapter.CustomPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CarrouselArmario extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_outfits_armarios);

        ViewPager viewPager = findViewById(R.id.viewPager);
        Button button = findViewById(R.id.button3);



        // va la lista de las fotos de los outfits
       List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.camiseta);
        imageList.add(R.drawable.pantalon);
        imageList.add(R.drawable.zapas);
        imageList.add(R.drawable.camiseta);
        imageList.add(R.drawable.pantalon);
        imageList.add(R.drawable.zapas);
        imageList.add(R.drawable.camiseta);
        imageList.add(R.drawable.pantalon);
        imageList.add(R.drawable.zapas);
        imageList.add(R.drawable.camiseta);
        imageList.add(R.drawable.pantalon);
        imageList.add(R.drawable.zapas);

        CustomPagerAdapter adapter = new CustomPagerAdapter(this, imageList);
        viewPager.setAdapter(adapter);


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


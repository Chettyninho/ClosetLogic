package com.example.goodcloset;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.goodcloset.Adapter.CustomPagerAdapter;
import com.example.goodcloset.modelos.ArmarioModelo;

import java.util.ArrayList;
import java.util.List;

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


        // Luego puedes utilizar este idArmario según tus necesidades en la actividad

        // Aquí debes inicializar tu lista de imágenes o realizar cualquier otra lógica necesaria

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


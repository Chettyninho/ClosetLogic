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
       /* List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.foto1);
        imageList.add(R.drawable.foto2);
        imageList.add(R.drawable.foto3);
        imageList.add(R.drawable.foto1);
        imageList.add(R.drawable.foto2);
        imageList.add(R.drawable.foto3);
        imageList.add(R.drawable.foto1);
        imageList.add(R.drawable.foto2);
        imageList.add(R.drawable.foto3);
        imageList.add(R.drawable.foto1);
        imageList.add(R.drawable.foto2);
        imageList.add(R.drawable.foto3);

        CustomPagerAdapter adapter = new CustomPagerAdapter(this, imageList);
        viewPager.setAdapter(adapter);
        */

    }
}

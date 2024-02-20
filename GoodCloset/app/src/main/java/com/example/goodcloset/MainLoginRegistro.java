package com.example.goodcloset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.goodcloset.Fragments.FragmentLogIn;
import com.example.goodcloset.Fragments.FragmentRegistro;
import com.google.android.material.tabs.TabLayout;
import com.bumptech.glide.Glide;

public class MainLoginRegistro extends AppCompatActivity {

    ImageView imgbg;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login_registro);

        imgbg = findViewById(R.id.registrobg);

        // Cargar la imagen con Glide y animación de fade in
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_background);
        imgbg.startAnimation(fadeInAnimation);
        Glide.with(this)
                .load(R.drawable.registrobg)
                .into(imgbg);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // Configurando el ViewPager
        GestorFragments gestorFragments = new GestorFragments(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        gestorFragments.addFragment(new FragmentLogIn(), "LOGIN");
        gestorFragments.addFragment(new FragmentRegistro(), "REGISTRO");
        viewPager.setAdapter(gestorFragments);
    }
}

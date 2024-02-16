package com.example.goodcloset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

public class MainLoginRegistro extends AppCompatActivity {

    ImageView imgbg;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login_registro);

        imgbg = findViewById(R.id.registrobg);
        imgbg.animate().translationY(-2220).setDuration(1250).setStartDelay(0);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        com.example.goodcloset.GestorFragments gestorFragments = new com.example.goodcloset.GestorFragments(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        gestorFragments.addFragment(new com.example.goodcloset.Fragments.FragmentLogIn(), "LOGIN");
        gestorFragments.addFragment(new com.example.goodcloset.Fragments.FragmentRegistro(), "REGISTRO");
        viewPager.setAdapter(gestorFragments);

    }
}

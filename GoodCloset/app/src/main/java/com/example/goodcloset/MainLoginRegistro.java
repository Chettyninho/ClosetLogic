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

    GestorFragments pageAdatper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login_registro);

        imgbg = findViewById(R.id.registrobg);
        imgbg.animate().translationY(-2100).setDuration(1000).setStartDelay(0);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        Animation animation  =  AnimationUtils.loadAnimation(this,R.anim.slideup);
        tabLayout.startAnimation(animation);
        viewPager.startAnimation(animation);

        // Configurando el ViewPager
        pageAdatper = new GestorFragments( getSupportFragmentManager(),tabLayout.getTabCount());
        pageAdatper.addFragment(new FragmentLogIn(), "LOGIN");
        pageAdatper.addFragment(new FragmentRegistro(), "REGISTRO");
        viewPager.setAdapter(pageAdatper);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0){
                    pageAdatper.notifyDataSetChanged();
                }
                if(tab.getPosition() == 1){
                    pageAdatper.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}

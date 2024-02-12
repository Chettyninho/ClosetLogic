package com.example.goodcloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    ImageView logo, imgbg;
    TextView appname,powered;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //logo = findViewById(R.id.logo);
        imgbg = findViewById(R.id.img);
        appname = findViewById(R.id.appName);
        lottieAnimationView = findViewById(R.id.lottie);
        powered = findViewById(R.id.powered);

        imgbg.animate().translationY(-2500).setDuration(1000).setStartDelay(3000);
        //logo.animate().translationY(-1400).setDuration(1000).setStartDelay(4000);
        appname.animate().translationY(-1400).setDuration(1000).setStartDelay(3000);
        lottieAnimationView.animate().translationY(-1700).setDuration(1000).setStartDelay(3000);
        powered.animate().translationY(-1900).setDuration(1000).setStartDelay(3000);

        final Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.slideleft);
        final Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.slideright);
        final Animation animacion3 = AnimationUtils.loadAnimation(this, R.anim.slideup);

        appname.startAnimation(animacion2);
        lottieAnimationView.startAnimation(animacion1);
        powered.startAnimation(animacion3);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, Registro.class));
                finish();
            }
        }, 4500);

    }
}

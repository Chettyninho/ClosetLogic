package com.example.goodcloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
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

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float density = displayMetrics.density;
        int screenHeightPx = displayMetrics.heightPixels;

        int translationBg = -(int)(density * 1000); // Ajuste de 1000dp
        int translationAppName = -(int)(density * 700); // Ajuste de 700dp
        int translationLottie = -(int)(density * 850); // Ajuste de 850dp
        int translationPowered = -(int)(density * 950); // Ajuste de 950dp

        imgbg.animate().translationY(translationBg).setDuration(800).setStartDelay(3000);
        appname.animate().translationY(translationAppName).setDuration(800).setStartDelay(3000);
        lottieAnimationView.animate().translationY(translationLottie).setDuration(800).setStartDelay(3000);
        powered.animate().translationY(translationPowered).setDuration(800).setStartDelay(3000);

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
        }, 4100);

    }
}

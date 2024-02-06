package com.example.goodcloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Registro extends AppCompatActivity {

    ImageView imgbg;
    TextView txtView,name,surname,email,username,password, button;
    LinearLayout ly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        imgbg = findViewById(R.id.registrobg);
        txtView = findViewById(R.id.title);
        ly = findViewById(R.id.linearLayout);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.apellido);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        button = findViewById(R.id.enviar);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float density = displayMetrics.density;
        int screenHeightPx = displayMetrics.heightPixels;

        int translationBg = -(int)(density * 800); // Ajuste de 1000dp


        imgbg.animate().translationY(translationBg).setDuration(800).setStartDelay(0);


        Animation aniFade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideleft);
        txtView.startAnimation(aniFade);

        Animation aniFade2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideright);
        name.startAnimation(aniFade2);

        Animation aniFade3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideleft);
        surname.startAnimation(aniFade3);

        Animation aniFade4 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideright);
        email.startAnimation(aniFade4);

        Animation aniFade5 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideleft);
        username.startAnimation(aniFade5);

        Animation aniFade6 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideright);
        password.startAnimation(aniFade6);

        Animation aniFade7 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        button.startAnimation(aniFade7);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Registro.this, MainActivity.class));
                finish();
            }
        }, 4500);

    }
}
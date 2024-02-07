package com.example.goodcloset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LogIn extends AppCompatActivity {
    ImageView imgbg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        imgbg = findViewById(R.id.loginbg);
        imgbg.animate().translationY(BIND_ABOVE_CLIENT).setDuration(1000).setStartDelay(3000);
    }

}
package com.example.goodcloset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.goodcloset.Fragments.AddFragment;
import com.example.goodcloset.Fragments.HomeFragment;
import com.example.goodcloset.Fragments.ProfileFragment;
import com.example.goodcloset.Fragments.SearchFragment;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.Retrofit.SingletonUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    //RespuestaInsertarUsuario usuarioRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new HomeFragment(), true);

        //Intent intent = getIntent();
        //if(intent.hasExtra("usuarioRegistrado")){
        //     usuarioRecibido = (RespuestaInsertarUsuario) intent.getSerializableExtra("usuarioRegistrado");
//        }
//Log.d("EN EL MAIN:", ""+usuarioRecibido.getEmail().toString());
        ////////////////////////////////////////////////
        //recuperacion de los datos del singleton creado al inicar sesion
        RespuestaInsertarUsuario usuarioRecibido = SingletonUser.getInstance().getUsuario();
        if (usuarioRecibido != null) {
            Log.d("EN EL MAIN con singleton:", "El id es: "+usuarioRecibido.getId());
        } else {
            Log.d("EN EL MAIN con singleton:", "fallo al recuperar el singleton");
        }

        /////////////////////////////////////////////////
        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.frameLayout);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.navHome) {

                    loadFragment(new HomeFragment(), true);

                } else if (itemId == R.id.navProfile) {

                    loadFragment(new ProfileFragment(), false);

                } else if (itemId == R.id.navAdd){ // nav Add

                    loadFragment(new AddFragment(), false);

                }
                else {

                    loadFragment(new SearchFragment(), false);

                }

                return true;
            }
        });

    }
    private void loadFragment(Fragment fragment, boolean isAppInitialized) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(isAppInitialized){
            fragmentTransaction.add(R.id.frameLayout, fragment);
        } else {
            fragmentTransaction.replace(R.id.frameLayout, fragment);
        }
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}
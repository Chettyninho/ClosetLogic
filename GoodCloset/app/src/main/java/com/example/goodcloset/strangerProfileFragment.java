package com.example.goodcloset;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.goodcloset.Adapter.LupaAdapter;
import com.example.goodcloset.R;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.Retrofit.SingletonUser;
import com.example.goodcloset.methodLayer.ArmarioMethods;
import com.example.goodcloset.modelos.ArmarioModelo;
import com.example.goodcloset.modelos.UsuarioModelo;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class strangerProfileFragment extends AppCompatActivity {
    TextView nombreUser, NumeroFollower,NumeroFollow, NumeroArmarios;


    ImageView imagenPerfil;
    static Button seguirButton;
    private RespuestaInsertarUsuario usuario = SingletonUser.getInstance().getUsuario();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    UsuarioModelo usuarioRecibido;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_stranger_profile);
        Intent intent = getIntent();
        //ApiService apiService = ApiClient.getInstance().getApiService();
       // RespuestaInsertarUsuario usuarioSingleton = SingletonUser.getInstance().getUsuario();

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(viewPager);
        usuarioRecibido = (UsuarioModelo) getIntent().getSerializableExtra("usuario");

        com.example.goodcloset.GestorFragments gestorFragments = new com.example.goodcloset.GestorFragments(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        gestorFragments.addFragment(new com.example.goodcloset.Fragments.FragmentVerPerfil(), "PERFIL");
        Log.d("strngerprofileFragment", "" + usuarioRecibido);
        gestorFragments.addFragment(new com.example.goodcloset.Fragments.FragmentVerArmarios(usuarioRecibido), "ARMARIOS");
        viewPager.setAdapter(gestorFragments);

        Log.d("test","usuarioRecibido ->" + usuarioRecibido.toString());
        nombreUser = findViewById(R.id.usernameTextView);
        NumeroFollower = findViewById(R.id.followersTextView);
        NumeroFollow = findViewById(R.id.tvNumeroSeguidos);
        NumeroArmarios = findViewById(R.id.tvNumeroArmarios);
        imagenPerfil = findViewById(R.id.profileImageViewProfileExtrano);
        establecerDatosDelUsuarioEnLaVista(usuarioRecibido);


        //////////////////
        ApiService apiService = ApiClient.getInstance().getApiService();
        RespuestaInsertarUsuario usuarioSingleton = SingletonUser.getInstance().getUsuario();

        seguirButton = findViewById(R.id.seguirButton);
        seguirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                follow(apiService,usuarioSingleton,usuarioRecibido);
            }
        });
    }


    private void establecerDatosDelUsuarioEnLaVista(UsuarioModelo usuarioRecibido) {
        nombreUser.setText("@" + usuarioRecibido.getUserName());
        NumeroFollower.setText(String.valueOf(usuarioRecibido.getContador_seguidores()));
        NumeroFollow.setText(String.valueOf(usuarioRecibido.getContador_seguidos()));
        NumeroArmarios.setText(String.valueOf(usuarioRecibido.getContador_armarios()));
        // Decodificar la cadena de la imagen en un array de bytes
        Log.d("referencia imagen", imagenPerfil.toString());
        String fotoUsuarioBase64 = usuarioRecibido.getFotoUsuario();
        if (fotoUsuarioBase64 != null) {
            Bitmap imagenPerfilBitmap = ArmarioMethods.convertirBase64ABitmap(fotoUsuarioBase64);
            imagenPerfil.setImageBitmap(imagenPerfilBitmap);
        } else {
            imagenPerfil.setImageResource(R.drawable.baseline_person_24);
        }

    }

    private static void follow(ApiService apiService,RespuestaInsertarUsuario usuarioSingleton,UsuarioModelo usuarioExtraño){
        Call<Void> follow = apiService.follow4Follow(usuarioExtraño.getId(),usuarioSingleton.getId());
        follow.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                seguirButton.setText("Seguido");
                seguirButton.setTextColor(R.color.white);
                seguirButton.setBackgroundColor(R.color.SecondColor);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


}

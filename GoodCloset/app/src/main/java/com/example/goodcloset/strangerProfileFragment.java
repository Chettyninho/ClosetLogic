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

        com.example.goodcloset.GestorFragments gestorFragments = new com.example.goodcloset.GestorFragments(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        gestorFragments.addFragment(new com.example.goodcloset.Fragments.FragmentVerPerfil(), "PERFIL");
        gestorFragments.addFragment(new com.example.goodcloset.Fragments.FragmentVerArmarios(), "ARMARIOS");
        viewPager.setAdapter(gestorFragments);

        usuarioRecibido = (UsuarioModelo) getIntent().getSerializableExtra("usuario");
        Log.d("test","usuarioRecibido ->" + usuarioRecibido.toString());
        nombreUser = findViewById(R.id.usernameTextView);
        NumeroFollower = findViewById(R.id.followersTextView);
        NumeroFollow = findViewById(R.id.tvNumeroSeguidos);
        NumeroArmarios = findViewById(R.id.tvNumeroArmarios);
        imagenPerfil = findViewById(R.id.profileImageViewProfileExtraño);
        establecerDatosDelUsuarioEnLaVista(usuarioRecibido);


        //////////////////
        ApiService apiService = ApiClient.getInstance().getApiService();
        RespuestaInsertarUsuario usuarioSingleton = SingletonUser.getInstance().getUsuario();


        //HAY QUE TENER UN USUARIO PARA RECOGER LOS DATOS QUE SE LE PASARAN DESDE LA OTRA ACTIVIADA
        //POR EJEMPLO:
        //USUARIOMODELO USUAROeXTRAÑO = /*USUAARIO QUE SE RECIBA DE LA OTRA VISTA*/

        //aqui habra que ajustar la lógica
        //para que cuando hayamos hecho click en algun perfil...
        //cargará esta vista trayendo los datos del usuario que hayamos clicado

        //COSAS POR HACER EN ESTA CLASE:
        //Endopoint para seguir a un usuario (post a tabla seguidor)

        //llamada para recuperar armarios de un id(en este caso del que hayamos clickado)
        //ver si se puede aprovechar profileArmarioAdapter y su vista para pintar los datos (imagino q si pq no se usan a la vez...)

        //opcional:
        //poner boton dentro de los armarios para añadir un outfit al tuyo (nuestros armarios los tenemos, los de la persona tambien seria solo añador un outfit a nuestro armario)

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
        if (usuarioRecibido.getFotoUsuario() != null) {
            try {
                // Decodificar la cadena Base64 en un array de bytes
                byte[] imagenBytes = Base64.decode(usuarioRecibido.getFotoUsuario(), Base64.DEFAULT);

                Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);

                // Establecer el Bitmap en el ImageView
                imagenPerfil.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
                // Manejar el error, por ejemplo, establecer una imagen predeterminada
                // imagenPerfil.setImageResource(R.drawable.imagen_predeterminada);
            }
        } else {
            // Manejar el caso en que la cadena de la imagen sea nula
            // Por ejemplo, puedes establecer una imagen predeterminada o hacer alguna otra acción
            // imagenPerfil.setImageResource(R.drawable.imagen_predeterminada);
        }
    }

    private static void follow(ApiService apiService,RespuestaInsertarUsuario usuarioSingleton,UsuarioModelo usuarioExtraño){
        Call<Void> follow = apiService.follow4Follow(usuarioExtraño.getId(),usuarioSingleton.getId());
        follow.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                seguirButton.setText("Seguido");
                seguirButton.setBackgroundColor(R.color.seguidoButton);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


}

package com.example.goodcloset.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.goodcloset.R;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.Retrofit.SingletonUser;
import com.example.goodcloset.modelos.UsuarioModelo;

public class strangerProfileFragment extends Fragment {
    Button seguirButton;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stranger_profile, container, false);

        //llamadas ApiService y Singleton
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
        seguirButton = rootView.findViewById(R.id.seguirButton);
        seguirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                follow(apiService,usuarioSingleton/*,usuarioExtraño*/);
            }
        });
        //llamada para recuperar armarios de un id(en este caso del que hayamos clickado)
        //ver si se puede aprovechar profileArmarioAdapter y su vista para pintar los datos (imagino q si pq no se usan a la vez...)

        //opcional:
        //poner boton dentro de los armarios para añadir un outfit al tuyo (nuestros armarios los tenemos, los de la persona tambien seria solo añador un outfit a nuestro armario)
        return rootView;
    }

    private static void follow(ApiService apiService,RespuestaInsertarUsuario usuarioSingleton/*,UsuarioModel usuarioExtraño*/){
//aqui hay fallo porque usuaro extraño see obtienen al hacer click en la vista anterior
        //apiService.follow4Follow(usuarioExtraño.getID,usuarioSingleton.getId());


    }
}

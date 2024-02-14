package com.example.goodcloset.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.goodcloset.R;

public class strangerProfileFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stranger_profile, container, false);

        //aqui habra que ajustar la lógica
        //para que cuando hayamos hecho click en algun perfil...
        //cargará esta vista trayendo los datos del usuario que hayamos clicado

        //COSAS POR HACER EN ESTA CLASE:
        //Endopoint para seguir a un usuario (post a tabla seguidor)
        //llamada para recuperar armarios de un id(en este caso del que hayamos clickado)
        //ver si se puede aprovechar profileArmarioAdapter y su vista para pintar los datos (imagino q si pq no se usan a la vez...)

        //opcional:
        //poner boton dentro de los armarios para añadir un outfit al tuyo (nuestros armarios los tenemos, los de la persona tambien seria solo añador un outfit a nuestro armario)
        return rootView;
    }
    }

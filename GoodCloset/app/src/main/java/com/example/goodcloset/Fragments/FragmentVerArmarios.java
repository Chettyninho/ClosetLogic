package com.example.goodcloset.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.goodcloset.R;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.Retrofit.SingletonUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link //FragmentVerArmarios#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentVerArmarios extends Fragment {
    RespuestaInsertarUsuario usuario = SingletonUser.getInstance().getUsuario();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ver_armarios, container, false);

    }
}
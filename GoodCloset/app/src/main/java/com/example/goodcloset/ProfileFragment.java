package com.example.goodcloset;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import com.google.android.material.tabs.TabLayout;

public class  ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        // Obtener referencia al TabLayout desde el diseño inflado
        TabLayout tabLayout = rootView.findViewById(R.id.tabLayout);

        // Agregar pestañas al TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Sección 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Sección 2"));

        return rootView;
    }
}

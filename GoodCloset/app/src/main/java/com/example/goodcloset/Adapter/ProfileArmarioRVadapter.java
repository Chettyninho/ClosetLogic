package com.example.goodcloset.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodcloset.modelos.UsuarioModelo;

import java.util.ArrayList;

public class ProfileArmarioRVadapter  extends RecyclerView.Adapter<ProfileArmarioRVadapter.MyViewHolder>{
    Context context;
    ArrayList<UsuarioModelo> usuarios;

    public ProfileArmarioRVadapter(Context context, ArrayList<UsuarioModelo> usuarios) {
        this.context = context;
        this.usuarios = usuarios;
    }
    @NonNull
    @Override
    public ProfileArmarioRVadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileArmarioRVadapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        //cuando este hecho el modelo del row que ira aqui  se meten los textsaviews y el imageview

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

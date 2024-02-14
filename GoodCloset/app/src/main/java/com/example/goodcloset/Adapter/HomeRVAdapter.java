package com.example.goodcloset.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodcloset.R;
import com.example.goodcloset.modelos.UsuarioModelo;

import java.util.ArrayList;

public class HomeRVAdapter extends RecyclerView.Adapter<HomeRVAdapter.MyViewHolder>{
    Context context;
    ArrayList<UsuarioModelo> usuarios;

    public HomeRVAdapter(Context context, ArrayList<UsuarioModelo> usuarios) {
        this.context = context;
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public HomeRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.image_row,parent,false);
        return new HomeRVAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRVAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvPieDeFoto,tvUserName;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvPieDeFoto = itemView.findViewById(R.id.tvPieDeFoto);
            this.tvUserName = itemView.findViewById(R.id.tvUserNameHome);
            this.imageView = itemView.findViewById(R.id.imageViewArmarioUser);
        }
    }

}

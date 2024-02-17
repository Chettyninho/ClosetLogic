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
import com.example.goodcloset.modelos.ArmarioModelo;
import com.example.goodcloset.modelos.UsuarioModelo;

import java.util.ArrayList;
import java.util.List;

public class ProfileArmarioRVadapter  extends RecyclerView.Adapter<ProfileArmarioRVadapter.MyViewHolder>{
    Context context;
    ArrayList<ArmarioModelo> armarios;

    public ProfileArmarioRVadapter(Context context, ArrayList<ArmarioModelo> armarios) {
        this.context = context;
        this.armarios = armarios;
    }
    @NonNull
    @Override
    public ProfileArmarioRVadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.armariorv_row,parent,false);
        return new ProfileArmarioRVadapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileArmarioRVadapter.MyViewHolder holder, int position) {
        ArmarioModelo armario = armarios.get(position);
        holder.nombreArmarioTextView.setText(armario.getNombre_armario());
        holder.armarioIconImageView.setImageResource(R.drawable.armario);
    }

    @Override
    public int getItemCount() {
        return armarios.size();
    }

    public void actualizarLista(List<ArmarioModelo> nuevaLista) {
        armarios.clear();
        armarios.addAll(nuevaLista);
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView armarioIconImageView;
        TextView nombreArmarioTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.armarioIconImageView = itemView.findViewById(R.id.iconArmarioImageView);
            this.nombreArmarioTextView = itemView.findViewById(R.id.nombreArmariotv);

        }
    }
}
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
import java.util.List;

public class LupaAdapter extends RecyclerView.Adapter<LupaAdapter.MyViewHolder> {

    Context context;
    ArrayList<UsuarioModelo> itemArray;


    public LupaAdapter(Context context, ArrayList<UsuarioModelo> itemArray) {
        this.context = context;
        this.itemArray = itemArray;
    }

    public void setFilteredLsit(ArrayList<UsuarioModelo> filteredList){

        this.itemArray = filteredList;
        notifyDataSetChanged();

    }

    public void actualizarLista(List<UsuarioModelo> nuevaLista) {
        itemArray.clear();
        itemArray.addAll(nuevaLista);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cv_example, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder.img.setImageDrawable(itemArray.get(position).getFotoUsuario());
        holder.text.setText(itemArray.get(position).getUserName());
        //holder.text.setText(itemArray.get(position).getText2());
    }

    @Override
    public int getItemCount() {
        return itemArray.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView text;

        ImageView img;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textViewPrueba);
            img = itemView.findViewById(R.id.imageViewPrueba);
        }
    }
}

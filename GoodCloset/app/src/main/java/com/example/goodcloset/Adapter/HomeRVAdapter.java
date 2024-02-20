package com.example.goodcloset.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodcloset.ExampleItem;
import com.example.goodcloset.R;
import com.example.goodcloset.modelos.ArmarioModelo;
import com.example.goodcloset.modelos.UsuarioModelo;

import java.util.ArrayList;
import java.util.List;

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

        UsuarioModelo user = usuarios.get(position);
        //String imagenCodificada = user.getFotoUsuario();
        //byte[] decodedString = Base64.decode(imagenCodificada, Base64.DEFAULT);
        //Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
       // holder.imageView.setImageBitmap(bitmap);
        holder.tvUserName.setText(user.getUserName());

        //holder..setText(user.getUserName());

    }

    public void setFilteredLsit(ArrayList<UsuarioModelo> filteredList){

        this.usuarios = filteredList;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public void actualizarLista(List<UsuarioModelo> nuevaLista) {
        usuarios.clear();
        usuarios.addAll(nuevaLista);
        notifyDataSetChanged();
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

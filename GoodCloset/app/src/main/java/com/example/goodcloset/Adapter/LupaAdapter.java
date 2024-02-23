package com.example.goodcloset.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodcloset.methodLayer.ArmarioMethods;
import com.example.goodcloset.strangerProfileFragment;
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
            UsuarioModelo usuario = itemArray.get(position);
          holder.text.setText(itemArray.get(position).getUserName());

          holder.card.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  UsuarioModelo usernameSeleccionado = itemArray.get(position);
                  Intent intent = new Intent(view.getContext(), strangerProfileFragment.class);
                  Log.d("AAAAAAAAAA", "AAAAAAAAA: " + usernameSeleccionado);
                  intent.putExtra("usuario",usernameSeleccionado);
                  view.getContext().startActivity(intent);
              }
          });

        // Aquí obtienes la imagen del usuario si está disponible
        if (usuario.getFotoUsuario() != null) {
            Bitmap imagenPerfilBitmap = ArmarioMethods.convertirBase64ABitmap(usuario.getFotoUsuario());
            // Suponiendo que holder.image es la ImageView donde deseas mostrar la imagen
            holder.image.setImageBitmap(imagenPerfilBitmap);
        } else {
            // Si no hay imagen de usuario disponible, puedes establecer un icono predeterminado o dejar la imagen vacía
            holder.image.setImageResource(R.drawable.avatar);
            // O puedes dejar la imagen vacía
            // holder.image.setImageDrawable(null);
        }
    }

    @Override
    public int getItemCount() {
        return itemArray.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        CardView card;
        TextView text;
        ImageView image;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardSearch);
            text = itemView.findViewById(R.id.textViewPrueba);
            image = itemView.findViewById(R.id.imageViewPrueba);
        }
    }
}

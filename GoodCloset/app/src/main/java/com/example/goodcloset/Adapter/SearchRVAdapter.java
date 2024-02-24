package com.example.goodcloset.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodcloset.R;
import com.example.goodcloset.modelos.UsuarioModelo;
import com.example.goodcloset.strangerProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchRVAdapter extends RecyclerView.Adapter<SearchRVAdapter.MyViewHolder> {

    Context context;
    ArrayList<UsuarioModelo> usuarios;

    public SearchRVAdapter(Context context, ArrayList<UsuarioModelo> usuarios) {
        this.context = context;
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public SearchRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_row,parent,false);
        return new SearchRVAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRVAdapter.MyViewHolder holder, int position) {
        UsuarioModelo user = usuarios.get(position);
        if (user.getFotoUsuario() != null) {
            Log.d("foto", user.getFotoUsuario().toString());
            try {
                // Decodificar la cadena Base64 en un array de bytes
                byte[] imagenBytes = Base64.decode(user.getFotoUsuario(), Base64.DEFAULT);

                Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);

                // Establecer el Bitmap en el ImageViewç
                holder.imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
                // Manejar el error, por ejemplo, establecer una imagen predeterminada
                // imagenPerfil.setImageResource(R.drawable.imagen_predeterminada);
            }
        } else {
            holder.imageView.setImageResource(R.drawable.defaultimg);
        }


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioModelo usernameSeleccionado = usuarios.get(position);
                Intent intent = new Intent(view.getContext(), strangerProfileFragment.class);
                Log.d("AAAAAAAAAA", "AAAAAAAAA: " + usernameSeleccionado);
                intent.putExtra("usuario",usernameSeleccionado);
                view.getContext().startActivity(intent);
            }
        });

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

        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageFotoUser);
        }
    }

}

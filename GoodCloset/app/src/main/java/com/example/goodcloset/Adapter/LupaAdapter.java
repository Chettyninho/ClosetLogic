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
            byte[] imagenBytes = Base64.decode(usuario.getFotoUsuario(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);

            // Aplicar la forma circular a la imagen
            Bitmap circularBitmap = roundedCornerBitmap(bitmap);

            // Establecer el Bitmap circular en el ImageView
            holder.image.setImageBitmap(circularBitmap);
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

    private Bitmap roundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.BLACK); // Puedes ajustar el color según tus necesidades
        canvas.drawCircle(bitmap.getWidth() / 2f, bitmap.getHeight() / 2f, bitmap.getWidth() / 2f, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
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

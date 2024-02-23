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

import com.example.goodcloset.ExampleItem;
import com.example.goodcloset.R;
import com.example.goodcloset.modelos.ArmarioModelo;
import com.example.goodcloset.modelos.UsuarioModelo;
import com.example.goodcloset.strangerProfileFragment;

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
        if (user.getFotoUsuario() != null) {
            Log.d("foto", user.getFotoUsuario().toString());
            try {
                // Decodificar la cadena Base64 en un array de bytes
                byte[] imagenBytes = Base64.decode(user.getFotoUsuario(), Base64.DEFAULT);

                Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);

                // Redondear la imagen antes de establecerla en el ImageView
                Bitmap roundedBitmap = roundedCornerBitmap(bitmap, 9); // Radio de 20 para las esquinas redondeadas

                // Establecer el Bitmap redondeado en el ImageView
                holder.imageView.setImageBitmap(roundedBitmap);
            } catch (Exception e) {
                e.printStackTrace();
                // Manejar el error, por ejemplo, establecer una imagen predeterminada
                // imagenPerfil.setImageResource(R.drawable.imagen_predeterminada);
            }
        } else {
            holder.imageView.setImageResource(R.drawable.defaultimg);
        }
        holder.tvUserName.setText("@"+user.getUserName());

        holder.tvUserName.setOnClickListener(new View.OnClickListener() {
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
    // Método para redondear las esquinas de un Bitmap
    private Bitmap roundedCornerBitmap(Bitmap bitmap, int cornerRadius) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.BLACK); // Ajusta el color según tus necesidades
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
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

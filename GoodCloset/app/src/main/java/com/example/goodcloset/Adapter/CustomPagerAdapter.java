package com.example.goodcloset.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.goodcloset.R;
import com.example.goodcloset.methodLayer.ArmarioMethods;
import com.example.goodcloset.modelos.OutfitModelo;
import com.example.goodcloset.modelos.PrendaModelo;

import java.util.List;

import androidx.annotation.NonNull;


public class CustomPagerAdapter extends PagerAdapter {
    private Context context;
    private List<OutfitModelo> outfitsDeArmario;

    public CustomPagerAdapter(Context context, List<OutfitModelo> outfitsDeArmario) {
        this.context = context;
        this.outfitsDeArmario = outfitsDeArmario;
    }

    @Override
    public int getCount() {
        return outfitsDeArmario.size() ; // Muestra tres imágenes a la vez
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_image, container, false);

        List<PrendaModelo> prendasDeOutfit = outfitsDeArmario.get(position).getPrendasDelOutfit();

        ImageView imageView1 = view.findViewById(R.id.imageView1);
        Log.d("0oooo","" + prendasDeOutfit.get(0).getFoto_prenda().toString());
        byte[] imagenBytes = Base64.decode(prendasDeOutfit.get(0).getFoto_prenda(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
        imageView1.setImageBitmap(bitmap);
        //imageView1.setImageBitmap(ArmarioMethods.convertirBase64ABitmap(prendasDeOutfit.get(0).getFoto_prenda()));

        ImageView imageView2 = view.findViewById(R.id.imageView2);
        Log.d("111110oooo","" + prendasDeOutfit.get(1).getFoto_prenda().toString());
        byte[] imagenBytes2 = Base64.decode(prendasDeOutfit.get(1).getFoto_prenda(), Base64.DEFAULT);
        Bitmap bitmap2 = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
        imageView2.setImageBitmap(bitmap2);
        //imageView2.setImageBitmap(ArmarioMethods.convertirBase64ABitmap(prendasDeOutfit.get(1).getFoto_prenda()));

        ImageView imageView3 = view.findViewById(R.id.imageView3);
        Log.d("22220oooo","" + prendasDeOutfit.get(2).getFoto_prenda().toString());
        byte[] imagenBytes3 = Base64.decode(prendasDeOutfit.get(2).getFoto_prenda(), Base64.DEFAULT);
        Bitmap bitmap3 = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
        imageView3.setImageBitmap(bitmap3);
        //imageView3.setImageBitmap(ArmarioMethods.convertirBase64ABitmap(prendasDeOutfit.get(2).getFoto_prenda()));


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

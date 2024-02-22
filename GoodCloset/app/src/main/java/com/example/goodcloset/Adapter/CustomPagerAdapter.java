package com.example.goodcloset.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.goodcloset.R;
import com.example.goodcloset.methodLayer.ArmarioMethods;
import com.example.goodcloset.modelos.OutfitModelo;
import com.example.goodcloset.modelos.PrendaModelo;

import java.util.List;

import androidx.annotation.NonNull;


public class CustomPagerAdapter extends PagerAdapter {
    private Context context;
    private List<OutfitModelo> outfitsDeArmario;
    private List<PrendaModelo> prendasDeOutfit;
    private ImageView imageView1,imageView2,imageView3;

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

        View view = LayoutInflater.from(context).inflate(R.layout.item_image, container, false);
        prendasDeOutfit = outfitsDeArmario.get(position).getPrendasDelOutfit();
        //OutfitModelo outfitModelo = outfitsDeArmario.get(position);

        imageView1 = view.findViewById(R.id.tvParteArrivaImage);


        //Log.d("0oooo","" + prendasDeOutfit.get(0).getFoto_prenda().toString());
        //byte[] imagenBytes = Base64.decode(prendasDeOutfit.get(0).getFoto_prenda(), Base64.DEFAULT);
        //Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
        //imageView1.setImageBitmap(bitmap);
        //loadImageAsync(imageView1, prendasDeOutfit.get(0).getFoto_prenda());

        //imageView1.setImageBitmap(ArmarioMethods.convertirBase64ABitmap(prendasDeOutfit.get(0).getFoto_prenda()));

        imageView2 = view.findViewById(R.id.tvParteMediaImage);
        //Log.d("111110oooo","" + prendasDeOutfit.get(1).getFoto_prenda().toString());
        //byte[] imagenBytes2 = Base64.decode(prendasDeOutfit.get(1).getFoto_prenda(), Base64.DEFAULT);
        //byte[] imagenBytes2 = Base64.decode("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", Base64.DEFAULT);

        //Bitmap bitmap2 = BitmapFactory.decodeByteArray(imagenBytes2, 0, imagenBytes2.length);
        //imageView2.setImageBitmap(bitmap2);
        //imageView2.setImageBitmap(ArmarioMethods.convertirBase64ABitmap(prendasDeOutfit.get(1).getFoto_prenda()));

        imageView3 = view.findViewById(R.id.tvParteAbajoImage);
        //Log.d("22220oooo","" + prendasDeOutfit.get(2).getFoto_prenda().toString());
        //byte[] imagenBytes3 = Base64.decode(prendasDeOutfit.get(2).getFoto_prenda(), Base64.DEFAULT);
        //Bitmap bitmap3 = BitmapFactory.decodeByteArray(imagenBytes3, 0, imagenBytes3.length);
        //Log.d("22220oooo","" + bitmap3.toString());

        //imageView3.setImageBitmap(bitmap3);

        //imageView3.setImageBitmap(ArmarioMethods.convertirBase64ABitmap(prendasDeOutfit.get(2).getFoto_prenda()));

        Glide.with(context)
                .asBitmap()
                .load(prendasDeOutfit.get(position).getFoto_prenda())
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        imageView1.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });

        Glide.with(context)
                .asBitmap()
                .load(prendasDeOutfit.get(position + 1).getFoto_prenda())
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        imageView2.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
        Glide.with(context)
                .asBitmap()
                .load(prendasDeOutfit.get(position + 2).getFoto_prenda())
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        imageView3.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}

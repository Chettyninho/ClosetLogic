package com.example.goodcloset.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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
import com.example.goodcloset.methodLayer.ImageUtils;
import com.example.goodcloset.modelos.OutfitModelo;
import com.example.goodcloset.modelos.PrendaModelo;

import java.util.Base64;
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

        ImageView imageView1 = view.findViewById(R.id.tvParteArrivaImage);
        ImageView imageView2 = view.findViewById(R.id.tvParteMediaImage);
        ImageView imageView3 = view.findViewById(R.id.tvParteAbajoImage);

        String fotoPrenda1 = prendasDeOutfit.get(0).getFoto_prenda();
        String fotoPrenda2 = prendasDeOutfit.get(1).getFoto_prenda();
        String fotoPrenda3 = prendasDeOutfit.get(2).getFoto_prenda();


        Bitmap bitmap1 = ImageUtils.decodeBase64(fotoPrenda1);
        Bitmap bitmap2 = ImageUtils.decodeBase64(fotoPrenda2);
        Bitmap bitmap3 = ImageUtils.decodeBase64(fotoPrenda3);

        Glide.with(context)
                .load(bitmap1)
                .into(imageView1);

        Glide.with(context)
                .load(bitmap2)
                .into(imageView2);

        Glide.with(context)
                .load(bitmap3)
                .into(imageView3);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}

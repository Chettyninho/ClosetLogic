package com.example.goodcloset.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
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
        return outfitsDeArmario.size() / 3; // Muestra tres imágenes a la vez
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

        for (int i = 0; i < outfitsDeArmario.size(); i++) {

            List<PrendaModelo> prendasDeOutfit = outfitsDeArmario.get(i).getPrendasDelOutfit();
            ImageView imageView1 = view.findViewById(R.id.imageView1);
            ImageView imageView2 = view.findViewById(R.id.imageView2);
            ImageView imageView3 = view.findViewById(R.id.imageView3);



            imageView1.setImageBitmap(ArmarioMethods.convertirBase64ABitmap(prendasDeOutfit.get(i).getFoto_prenda()));
            Log.d("IMagen1", ""+prendasDeOutfit.get(i).getFoto_prenda());
            imageView2.setImageBitmap(ArmarioMethods.convertirBase64ABitmap(prendasDeOutfit.get(i).getFoto_prenda()));
            imageView3.setImageBitmap(ArmarioMethods.convertirBase64ABitmap(prendasDeOutfit.get(i).getFoto_prenda()));


/*
            ImageView imageView2 = view.findViewById(R.id.imageView2);
            imageView2.setImageResource(outfitsDeArmario.get(position * 3 + 1));

            ImageView imageView3 = view.findViewById(R.id.imageView3);
            imageView3.setImageResource(outfitsDeArmario.get(position * 3 + 2));
*/
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}



/*
import android.content.Context;
        import android.graphics.BitmapFactory;
        import android.support.annotation.NonNull;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;

        import androidx.viewpager.widget.PagerAdapter;

        import com.example.goodcloset.R;

        import java.util.List;

public class CustomPagerAdapter extends PagerAdapter {
    private Context context;
    private List<byte[]> imageBytesList; // Lista de imágenes en formato binario

    public CustomPagerAdapter(Context context, List<byte[]> imageBytesList) {
        this.context = context;
        this.imageBytesList = imageBytesList;
    }

    @Override
    public int getCount() {
        return imageBytesList.size() / 3; // Muestra tres imágenes a la vez
    }

    @Override
    public boolean isViewFromObject(@android.support.annotation.NonNull View view, @android.support.annotation.NonNull Object object) {
        return view == object;
    }

    @android.support.annotation.NonNull
    @Override
    public Object instantiateItem(@android.support.annotation.NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_image, container, false);

        ImageView imageView1 = view.findViewById(R.id.imageView1);
        imageView1.setImageBitmap(BitmapFactory.decodeByteArray(imageBytesList.get(position * 3), 0, imageBytesList.get(position * 3).length));

        ImageView imageView2 = view.findViewById(R.id.imageView2);
        imageView2.setImageBitmap(BitmapFactory.decodeByteArray(imageBytesList.get(position * 3 + 1), 0, imageBytesList.get(position * 3 + 1).length));

        ImageView imageView3 = view.findViewById(R.id.imageView3);
        imageView3.setImageBitmap(BitmapFactory.decodeByteArray(imageBytesList.get(position * 3 + 2), 0, imageBytesList.get(position * 3 + 2).length));

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@android.support.annotation.NonNull ViewGroup container, int position, @android.support.annotation.NonNull Object object) {
        container.removeView((View) object);
    }
}*/









package com.example.goodcloset.Adapter;
/*
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.goodcloset.R;

import java.util.List;

import androidx.annotation.NonNull;


public class CustomPagerAdapter extends PagerAdapter {
    private Context context;
    private List<Integer> imageList;

    public CustomPagerAdapter(Context context, List<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size() / 3; // Muestra tres imágenes a la vez
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

        ImageView imageView1 = view.findViewById(R.id.imageView1);
        imageView1.setImageResource(imageList.get(position * 3));

        ImageView imageView2 = view.findViewById(R.id.imageView2);
        imageView2.setImageResource(imageList.get(position * 3 + 1));

        ImageView imageView3 = view.findViewById(R.id.imageView3);
        imageView3.setImageResource(imageList.get(position * 3 + 2));

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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.goodcloset.CarrouselArmario;
import com.example.goodcloset.R;
import com.example.goodcloset.YourImageModel;

import java.util.List;

public class CustomPagerAdapter extends PagerAdapter {
    private final List<YourImageModel> imageList;
    private final LayoutInflater layoutInflater;

    public CustomPagerAdapter(CarrouselArmario context, List<YourImageModel> imageList) {
        this.imageList = imageList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.item_image, container, false);

        ImageView imageView1 = view.findViewById(R.id.imageView1);
        imageView1.setImageBitmap(convertByteArrayToBitmap(imageList.get(position).getFotoArriba()));

        ImageView imageView2 = view.findViewById(R.id.imageView2);
        imageView2.setImageBitmap(convertByteArrayToBitmap(imageList.get(position).getFotoMedio()));

        ImageView imageView3 = view.findViewById(R.id.imageView3);
        imageView3.setImageBitmap(convertByteArrayToBitmap(imageList.get(position).getFotoAbajo()));

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private Bitmap convertByteArrayToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}







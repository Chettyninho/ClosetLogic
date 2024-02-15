package com.example.goodcloset.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.goodcloset.R;

import java.util.List;

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

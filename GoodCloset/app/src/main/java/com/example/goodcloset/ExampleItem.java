package com.example.goodcloset;

import android.graphics.drawable.Drawable;

public class ExampleItem {
    private Drawable imageResource;
    private String text1;
    private String text2;

    public ExampleItem(Drawable imageResource, String text1, String text2) {
        this.imageResource = imageResource;
        this.text1 = text1;
        this.text2 = text2;
    }

    public Drawable getImageResource() {
        return imageResource;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }
}
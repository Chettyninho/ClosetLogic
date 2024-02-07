package com.example.goodcloset;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    Button btnCamara;
    ImageView partearriba, partemedia, parteabajo;

    private ArrayList<Bitmap> imagenesCapturadas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        imagenesCapturadas = new ArrayList<>();

        partearriba = rootView.findViewById(R.id.parteAriba);
        partemedia = rootView.findViewById(R.id.parteMedia);
        parteabajo = rootView.findViewById(R.id.parteAbajo);

        partearriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camaraLauncher1.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
            }
        });
        partemedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camaraLauncher2.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
            }
        });
        parteabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camaraLauncher3.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
            }
        });
        return rootView;
    }

    ActivityResultLauncher<Intent> camaraLauncher1 = registerForActivityResult(new
            ActivityResultContracts.StartActivityForResult(), new
            ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Bundle extras = result.getData().getExtras();
                        Bitmap imgBitmap = (Bitmap) extras.get("data");
                        partearriba.setImageBitmap(imgBitmap);
                        imagenesCapturadas.add(imgBitmap);
                    }
                }
            });

    ActivityResultLauncher<Intent> camaraLauncher2 = registerForActivityResult(new
            ActivityResultContracts.StartActivityForResult(), new
            ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Bundle extras = result.getData().getExtras();
                        Bitmap imgBitmap = (Bitmap) extras.get("data");
                        partemedia.setImageBitmap(imgBitmap);
                        imagenesCapturadas.add(imgBitmap);
                    }
                }
            });

    ActivityResultLauncher<Intent> camaraLauncher3 = registerForActivityResult(new
            ActivityResultContracts.StartActivityForResult(), new
            ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Bundle extras = result.getData().getExtras();
                        Bitmap imgBitmap = (Bitmap) extras.get("data");
                        parteabajo.setImageBitmap(imgBitmap);
                        imagenesCapturadas.add(imgBitmap);
                    }
                }
            });
}
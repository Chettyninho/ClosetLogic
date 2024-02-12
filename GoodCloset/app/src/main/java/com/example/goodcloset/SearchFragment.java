package com.example.goodcloset;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    private ImageView imageView;
    private ApiService apiService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        imageView = rootView.findViewById(R.id.imageView);

        // Obtén la instancia de ApiService de tu ApiClient
        apiService  = ApiClient.getInstance().getApiService();

        // Realiza la llamada para obtener la imagen

        Call<ResponseBody> call = apiService.obtenerImagen();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        // Convierte el ResponseBody en un Bitmap
                        InputStream inputStream = response.body().byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        // Carga el Bitmap en el ImageView con Picasso
                        imageView.setImageBitmap(bitmap);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // Maneja el error
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Maneja el error
            }
        });
        return rootView;
    }


}
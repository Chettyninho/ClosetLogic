package com.example.goodcloset.PruebagetFoto;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.example.goodcloset.R;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.modelos.ArmarioModelo;
import com.example.goodcloset.modelos.PrendaModelo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetFotos extends AppCompatActivity {

    private ImageView imageViewFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_fotos);

        imageViewFoto = findViewById(R.id.imageViewFoto);

        rescatarDatosDeImg();

    }

    private void rescatarDatosDeImg() {
        ApiService apiService = ApiClient.getInstance().getApiService();

        if(apiService!= null){
            Call<PrendaModelo> call = apiService.getImgB64(8);// 1, es para la prueba, en realidad hay que meter -> usuario.getId()
            call.enqueue(new Callback<PrendaModelo>() {

                @Override
                public void onResponse(Call<PrendaModelo> call, Response<PrendaModelo> response) {
                    if (response.isSuccessful()) {
                        PrendaModelo Prenda_imgB64 = response.body();
                        if (Prenda_imgB64 != null ) {
                            Log.d("DEBUG, USUARIO DE LA IMAGEN", ":" + Prenda_imgB64);
                            Bitmap foto = convertirBase64ABitmap(Prenda_imgB64.getFoto_prenda());
                            imageViewFoto.setImageBitmap(foto);
                        } else {
                            Log.e("imagen", "Cuerpo de la respuesta vacío o nulo");
                        }
                    } else {
                        Log.e("imagen", "Error en la respuesta: " + response.code());
                        // Agrega más detalles del error si están disponibles
                        try {
                            Log.e("imagen", "Error body: " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }



                @Override
                public void onFailure(Call<PrendaModelo> call, Throwable t) {
                    Log.d("imagen",""+t);
                }


            });
        }
    }


    private Bitmap convertirBase64ABitmap(String imgB64) {
        byte[] decodedBytes = Base64.decode(imgB64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
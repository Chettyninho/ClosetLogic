package com.example.goodcloset;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
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

import com.example.goodcloset.modelos.Usuario;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.Retrofit.SingletonUser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFragment extends Fragment {

    Button btnCamara,selectCloset;
    ImageView partearriba, partemedia, parteabajo;

    private ArrayList<String> imagenesCapturadas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);

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
        //selectCloset.findViewById(R.id.btnSelectArmario);
        // Importa las clases necesarias

// ...



        //btn camera es para enviar el outfit. solo dejara enviar el outfit si se ha seleccionado un armario,
        //ya veremos como lo hacemos
        //btnCamara.findViewById(R.id.btnCamara);
        // btnCamara.setOnClickListener(new View.OnClickListener() {
        // @Override
        //public void onClick(View view) {
        //salta un pop up con los armarios que existen y un boton para crear otro si se quiere.
        //ademas de otro boton dentro del popup que sea para enviar, enviando el armario con el que se asicoara el outfit que mandemos:

        //    enviarOutfit(imagenesCapturadas);
        //  }
        //});
        return rootView;
    }

    private void enviarOutfit(ArrayList<Bitmap> imagenesCapturadas) {
        ApiService apiService = ApiClient.getInstance().getApiService();
        if (apiService != null) {
//aqui se aplica la logica para enviar el array de fotos
            //en la API se usará el id del armario(que se seleccionará en un alertDialog del AndroidStudio)
            //para relacionar el id del armario con el outfit, ademas habra que insertar las prendas en su tabla correspondiente

        }else {
            Log.d("ApiService", "= null en home Fragment");
        }
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
                        String imgEnBase64 = convertirBitmapABase64(imgBitmap);
                        imagenesCapturadas.add(imgEnBase64);
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
                        String imgEnBase64 = convertirBitmapABase64(imgBitmap);
                        imagenesCapturadas.add(imgEnBase64);
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
                        String imgEnBase64 = convertirBitmapABase64(imgBitmap);
                        imagenesCapturadas.add(imgEnBase64);
                    }
                }
            });


    private String convertirBitmapABase64(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] byteArrayImage = baos.toByteArray();
        return Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
    }

    // private List<RespuestaGetArmariosDeUsuario> obtenerArmariosdeUsuario(Integer idUsuario){ //id usuario se obtendra del singletonuser y se pasara cuando se llame a la funcion
    //   ApiService apiService = ApiClient.getInstance().getApiService();
    // if (apiService != null) {
    //   Call<RespuestaGetArmariosDeUsuario> call = apiService.getArmariosUser(idUsuario);
    //  call.enqueue(new Callback<RespuestaGetArmariosDeUsuario>() {
    //    RespuestaGetArmariosDeUsuario respuesta;
    //  @Override
    //public void onResponse(Call<RespuestaGetArmariosDeUsuario> call, Response<RespuestaGetArmariosDeUsuario> response) {
    //  if(response.isSuccessful()){
    //    RespuestaGetArmariosDeUsuario respuesta = response.body();
    //aqui habra que gestionaresta respuesta para establecer los recicledView dentro del alertDialog y seleccionar el armario
    //al que vamos a añadir el outfit

    //}

    //}

    //@Override
    //public void onFailure(Call<RespuestaGetArmariosDeUsuario> call, Throwable t) {

    //     }

    //   });
    //finn del if appi service != null
    //    }
    //  return
    // }

}
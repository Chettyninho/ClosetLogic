package com.example.goodcloset.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.goodcloset.R;
import com.example.goodcloset.methodLayer.ArmarioMethods;
import com.example.goodcloset.modelos.ArmarioModelo;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.Retrofit.SingletonUser;
import com.example.goodcloset.modelos.OutfitModelo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFragment extends Fragment {
    private Integer idArmario;
    Button btnEnviar, selectCloset;
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
        selectCloset = rootView.findViewById(R.id.btnSelectArmario);
        btnEnviar = rootView.findViewById(R.id.enviarOutfitButton);

        //BOTON PARA ENVIAR
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un AlertDialog builder
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Detalles del atuendo");
                builder.setMessage("Ingrese el nombre y descripción del atuendo");

                // Crear un LinearLayout container
                LinearLayout layout = new LinearLayout(getActivity());
                layout.setOrientation(LinearLayout.VERTICAL);

                // Agregar campos de texto EditText para el nombre y descripción del atuendo
                final EditText outfitName = new EditText(getActivity());
                outfitName.setInputType(InputType.TYPE_CLASS_TEXT);
                outfitName.setHint("Nombre del atuendo");
                layout.addView(outfitName);

                final EditText outfitDescription = new EditText(getActivity());
                outfitDescription.setInputType(InputType.TYPE_CLASS_TEXT);
                outfitDescription.setHint("Descripción del atuendo");
                layout.addView(outfitDescription);

                // Agregar el LinearLayout al AlertDialog
                builder.setView(layout);

                // Agregar un botón para enviar
                builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = outfitName.getText().toString();
                        String description = outfitDescription.getText().toString();
                        ApiService apiService = ApiClient.getInstance().getApiService();
                        if (apiService != null) {
                            enviarOutfit(imagenesCapturadas, idArmario, name, description);
                        }
                    }
                });

                // Mostrar el AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
//BOTON SELECCIONAR ARMARIO DONDE SE GUARDA EL OUTFIT
        selectCloset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService = ApiClient.getInstance().getApiService();
                RespuestaInsertarUsuario mainUsr = SingletonUser.getInstance().getUsuario();

                if (apiService != null) {

                    Call<List<ArmarioModelo>> call = apiService.getArmariosUser(mainUsr.getId());
                    call.enqueue(new Callback<List<ArmarioModelo>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<ArmarioModelo>> call, @NonNull Response<List<ArmarioModelo>> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                List<ArmarioModelo> armarioModeloList = response.body();
                                Log.d("responseBody Mario", armarioModeloList.toString());
                                List<String> nombresArmarios = ArmarioMethods.getNombresArmarios(armarioModeloList);
                                CharSequence[] items = nombresArmarios.toArray(new CharSequence[nombresArmarios.size()]);

                                // Crear el AlertDialog
                                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                                builder.setTitle("Selecciona un armario o crea uno nuevo")

                                        .setItems(items, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Acciones al hacer clic en un armario existente
                                                String armarioSeleccionado = nombresArmarios.get(which);
                                                idArmario=ArmarioMethods.devolverIdArmario(armarioSeleccionado,armarioModeloList);
                                            Log.d("id armario debug", "" + idArmario);
                                            }
                                        })

                                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });

                                // Mostrar el AlertDialog
                                builder.create().show();

                            } else {
                                try {
                                    Log.e("Error en la respuesta", response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<ArmarioModelo>> call, Throwable t) {
                            // Manejar error en la respuesta
                        }

                    });

                }


            }
        });
        // Importa las clases necesarias

        return rootView;
    }

    private void enviarOutfit(ArrayList<String> imagenesCapturadas, Integer idArmario,String name, String description) {
        ApiService apiService = ApiClient.getInstance().getApiService();
        if (apiService != null) {
            //aqui se aplica la logica para enviar el array de fotos
            //en vez de idArmario mejor Armario

            if (imagenesCapturadas.size() != 3) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Debe haber únicamente 3 fotos en el outfit", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Call<OutfitModelo> call = apiService.postOutfit(idArmario, imagenesCapturadas,name,description);

                call.enqueue(new Callback<OutfitModelo>() {

                    @Override
                    public void onResponse(Call<OutfitModelo> call, Response<OutfitModelo> response) {
                        if (response.isSuccessful()) {
                            Log.d("en onResponse" , " respuesta :" +response.body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<OutfitModelo> call, Throwable t) {
                        Log.d("Debug","estas en el onFailure");
                    }
                });
            }


            //para relacionar el id del armario con el outfit, ademas habra que insertar las prendas en su tabla correspondiente

        } else {
            Log.d("ApiService", "= null en home Fragment");
        }
    }

    //lanzar las camaras y guardar las fotos
    ActivityResultLauncher<Intent> camaraLauncher1 = registerForActivityResult(new
            ActivityResultContracts.StartActivityForResult(), new
            ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Bundle extras = result.getData().getExtras();
                        Bitmap imgBitmap = (Bitmap) extras.get("data");
                        partearriba.setImageBitmap(imgBitmap);
                        String imgEnBase64 = ArmarioMethods.convertirBitmapABase64(imgBitmap);
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
                        String imgEnBase64 = ArmarioMethods.convertirBitmapABase64(imgBitmap);
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
                        String imgEnBase64 = ArmarioMethods.convertirBitmapABase64(imgBitmap);
                        imagenesCapturadas.add(imgEnBase64);
                    }
                }
            });





}
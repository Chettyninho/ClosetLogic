package com.example.goodcloset.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.goodcloset.CarrouselArmario;
import com.example.goodcloset.EditarUsuario.EditUser;
import com.example.goodcloset.Fragments.FragmentVerArmarios;
import com.example.goodcloset.Fragments.FragmentVerPerfil;
import com.example.goodcloset.R;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.Retrofit.SingletonUser;
import com.example.goodcloset.methodLayer.ArmarioMethods;
import com.example.goodcloset.modelos.ArmarioModelo;
import com.example.goodcloset.modelos.UsuarioModelo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  ProfileFragment extends Fragment {
    TextView nombreUser, NumeroFollower,NumeroFollow, NumeroArmarios;//numero armarios se tendria que cargar en el onResponse.con un .size() de la lista recuperada.
    ApiService apiService;
    Button editarButton;
    RecyclerView recyclerView;
    List<ArmarioModelo> armariosList;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private  RespuestaInsertarUsuario usuario;
    RespuestaInsertarUsuario usuarioModifyProfileImage;
    ImageView profileImageDialog;
    TextView cambiarFotoButton;
    Button btnEnviarImgProfile;
    Button seguirdoDialogButton;
    ImageView profileImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        profileImageDialog =rootView.findViewById(R.id.profileImageView);
        //tabs
        tabLayout = rootView.findViewById(R.id.tabLayout);
        viewPager = rootView.findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        apiService = ApiClient.getInstance().getApiService();
        usuario = SingletonUser.getInstance().getUsuario();
        Log.e("Usuario que llega al Perfil de Fragments" , " " + usuario.toString());
        //obtenemos la referencia del boton de editar
        editarButton = rootView.findViewById(R.id.editarPerfil);


        editarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogs(usuario);
            }


        });

        // Obtener referencia al TabLayout y el boton flotante desde el diseño inflado
        TabLayout tabLayout = rootView.findViewById(R.id.tabLayout);
        FloatingActionButton newArmarioButton = rootView.findViewById(R.id.NewArmarioButton);


        newArmarioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNewArmarioDialog();
            }
        });


        Log.d("test","seguidores ->" + usuario.getContador_seguidores() + "// usrname -> " +usuario.getUserName());
        nombreUser = rootView.findViewById(R.id.usernameTextView);
        NumeroFollower = rootView.findViewById(R.id.followersTextView);
        NumeroFollow = rootView.findViewById(R.id.tvNumeroSeguidos);
        NumeroArmarios = rootView.findViewById(R.id.tvNumeroArmarios);
        rootView.findViewById(R.id.tabLayout);
        establecerDatosDelUsuarioEnLaVista(usuario);
        recuperarArmariosUsuario(apiService,usuario);
        tabLayout = rootView.findViewById(R.id.tabLayout);

        //imageview donde pinto la imagen del perfil
        profileImageView = rootView.findViewById(R.id.profileImageView);
        //mostrarFotoPerfil(profileImageView);

        return rootView;

    }



    private void establecerDatosDelUsuarioEnLaVista(RespuestaInsertarUsuario usuario) {
        Log.e("USUARIO EN PROFILE", "VALUE: " + usuario.toString());
        nombreUser.setText("@" + usuario.getUserName());
        NumeroFollower.setText(String.valueOf(usuario.getContador_seguidores()));
        NumeroFollow.setText(String.valueOf(usuario.getContador_seguidos()));
        NumeroArmarios.setText(String.valueOf(usuario.getContador_armarios()));
// Decodificar la cadena de la imagen en un array de bytes
        if (usuario.getFotoUsuario() != null) {
            Log.d("foto", usuario.getFotoUsuario().toString());
            try {
                // Decodificar la cadena Base64 en un array de bytes

                byte[] imagenBytes = Base64.decode(usuario.getFotoUsuario(), Base64.DEFAULT);

                Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);

                // Redondear la imagen antes de establecerla en el ImageView
                Bitmap roundedBitmap = roundedCornerBitmap(bitmap, 15); //radio para las esquinas

                profileImageDialog.setImageBitmap(roundedBitmap);
            } catch (Exception e) {
                e.printStackTrace();
                // Manejar el error, por ejemplo, establecer una imagen predeterminada
                // imagenPerfil.setImageResource(R.drawable.imagen_predeterminada);
            }
        } else {
            // Manejar el caso en que la cadena de la imagen sea nula
            // Por ejemplo, puedes establecer una imagen predeterminada o hacer alguna otra acción
            // imagenPerfil.setImageResource(R.drawable.imagen_predeterminada);
        }
    }

    private Bitmap roundedCornerBitmap(Bitmap bitmap, int cornerRadius) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.BLACK); // Ajusta el color según tus necesidades
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    private void setupViewPager(ViewPager viewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentVerArmarios(), "Ver Armarios");
        adapter.addFragment(new FragmentVerPerfil(), "Ver Perfil");
        viewPager.setAdapter(adapter);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public MyPagerAdapter(FragmentManager manager) {
            super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }

    public void recuperarArmariosUsuario(ApiService apiService, RespuestaInsertarUsuario usuario) {

        if(apiService != null){
            Call<List<ArmarioModelo>> call = apiService.getArmariosUser(1);// 1, es para la prueba, en realidad hay que meter -> usuario.getId()
            call.enqueue(new Callback<List<ArmarioModelo>>() {
                @Override
                public void onResponse( Call<List<ArmarioModelo>> call, Response<List<ArmarioModelo>> response) {
                    Log.d("TAG", "Código de respuesta: " + response.code());

                    if(response.isSuccessful()){
                        armariosList = response.body();
                        for (ArmarioModelo armarioModelo: armariosList) {
                            Log.d("armario_" +armarioModelo.getId(),"el nombre del armario es: " + armarioModelo.getNombre_armario());
                        }
                        //vamos a tener que trabajar con el Viewmodel y eso desde aqui.s
                    }else {
                        try {
                            Log.e("Error en la respuesta", response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<ArmarioModelo>> call, Throwable t) {

                }

            });
        }
    }

    private void showNewArmarioDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Nuevo Armario");

        // Crear un EditText para que el usuario ingrese el nombre del armario
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Nombre del Armario");
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Puedes realizar alguna validación o manipulación según sea necesario
            }
        });

        builder.setView(input);

        // Configurar los botones del diálogo
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i  ) {
                String armarioName = input.getText().toString();
                Log.d("Nuevo Armario", "Nombre del Armario: " + armarioName);

                ArmarioModelo armario = new ArmarioModelo();
                RespuestaInsertarUsuario usuario = SingletonUser.getInstance().getUsuario();//para obtener el id de nuestro ususario en accion
                armario.setId_propietario(usuario.getId());
                armario.setNombre_armario(armarioName);//armario nuevo solo cn nombre:

                ApiService apiService = ApiClient.getInstance().getApiService();
                if (apiService != null) {
                    Log.d("test","callback");
                    Call<ArmarioModelo> call = apiService.postArmariosUser(armario);
                    call.enqueue(new Callback<ArmarioModelo>() {

                        @Override
                        public void onResponse(Call<ArmarioModelo> call, Response<ArmarioModelo> response) {
                            Log.e("antes del if de succesfull", "Código de error: " + response.code());

                            if (response.isSuccessful()) {
                                ArmarioModelo respuesta = response.body();
                                Log.d("JSON de la respuesta", new Gson().toJson(respuesta));

                                Log.d("Respuesta Exitosa", String.valueOf(respuesta));
                            } else {
                                Log.e("Respuesta Erronea", "Código de error: " + response.code());
                            }

                        }

                        @Override
                        public void onFailure(Call<ArmarioModelo> call, Throwable t) {
                            Log.e("Respuesta Erronea", "Código de error: " + t.getMessage());
                        }

                    });
                }
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.show();
    }

    private void showDialogs(RespuestaInsertarUsuario usuario) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.buttom_sheet_diaolg);

        //profileImageDialog = dialog.findViewById(R.id.imagenDialog);
        cambiarFotoButton = dialog.findViewById(R.id.CambiarFotoButton);
        btnEnviarImgProfile = dialog.findViewById(R.id.btnEnviarImgProfile);
        seguirdoDialogButton = dialog.findViewById(R.id.btnGoToEdit);

        // Mostrar el diálogo
        dialog.show();

        // Ajustar propiedades de la ventana para la animación y la posición
        Window window = dialog.getWindow();
        if (window != null) {
            window.setWindowAnimations(R.style.DialogAnimation);
            window.setWindowAnimations(R.style.DialogAnimationExit);// Asigna la animación al diálogo
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.BOTTOM); // Alinea el diálogo en la parte inferior de la pantalla
        }
        cambiarFotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camaraLauncherIMG.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
                btnEnviarImgProfile.setVisibility(View.VISIBLE);

            }
        });
        btnEnviarImgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postToBBDDFotoPerfil(usuarioModifyProfileImage);
                Log.e("BOTON ENVIAR FOTO" , "OK");
                btnEnviarImgProfile.setVisibility(View.GONE);
            }
        });
        seguirdoDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), EditUser.class);
                startActivity(i);
            }
        });
    }

    ActivityResultLauncher<Intent> camaraLauncherIMG = registerForActivityResult(new
            ActivityResultContracts.StartActivityForResult(), new
            ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Bundle extras = result.getData().getExtras();
                        Bitmap imgBitmap = (Bitmap) extras.get("data");
                        //profileImageDialog.setImageBitmap(imgBitmap);
                        String imgEnBase64 = ArmarioMethods.convertirBitmapABase64(imgBitmap);
                        usuarioModifyProfileImage = new RespuestaInsertarUsuario(usuario.getId(), usuario.getNombre(), usuario.getSurname(), usuario.getEmail(), usuario.getUsername(), usuario.getContador_seguidores(), usuario.getContador_seguidos(), usuario.getContador_armarios(), usuario.getFotoUsuario());
                        usuarioModifyProfileImage.setFotoUsuario(imgEnBase64);
                        Log.e("Log de Fotoperfil", "Usuario que inertamos a la bbdd: " + usuario.toString());
                    }
                }
            });

    private void postToBBDDFotoPerfil(RespuestaInsertarUsuario usuarioModifyProfileImage) {
        if(apiService != null){
            Log.e("BOTON ENVIAR " , "DENTR DE LA FUNCION POST");
            Call<RespuestaInsertarUsuario> call = apiService.insertarImagenAUsuario(usuarioModifyProfileImage);
            call.enqueue(new Callback<RespuestaInsertarUsuario>() {

                @Override
                public void onResponse(Call<RespuestaInsertarUsuario> call, Response<RespuestaInsertarUsuario> response) {
                    Log.d("en onResponse" , " respuesta : de la foto añadida" +response.body().toString());
                }
                @Override
                public void onFailure(Call<RespuestaInsertarUsuario> call, Throwable t) {
                }
            });
        }
    }
}
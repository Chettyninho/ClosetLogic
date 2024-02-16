package com.example.goodcloset.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.goodcloset.EditarUsuario.EditUser;
import com.example.goodcloset.R;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.Retrofit.SingletonUser;
import com.example.goodcloset.modelos.ArmarioModelo;
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
    Button editarButton, siguiednoButton;
    RecyclerView recyclerView;
    List<ArmarioModelo> armariosList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        apiService = ApiClient.getInstance().getApiService();
        RespuestaInsertarUsuario usuario = SingletonUser.getInstance().getUsuario();
//obtenemos la referencia del boton de editar
        editarButton = rootView.findViewById(R.id.editarPerfil);
        siguiednoButton = rootView.findViewById(R.id.Seguido);


        siguiednoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogs(usuario);
            }


        });

        // Obtener referencia al TabLayout y el boton flotante desde el diseño inflado
        TabLayout tabLayout = rootView.findViewById(R.id.tabLayout);
        FloatingActionButton newArmarioButton = rootView.findViewById(R.id.NewArmarioButton);

        editarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), EditUser.class);
                startActivity(i);

            }
        });

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
        ImageView profileImageView = rootView.findViewById(R.id.profileImageView);
        mostrarFotoPerfil(profileImageView);

        return rootView;

    }

    private void establecerDatosDelUsuarioEnLaVista(RespuestaInsertarUsuario usuario) {
        nombreUser.setText("@" + usuario.getUserName());
        NumeroFollower.setText(String.valueOf(usuario.getContador_seguidores()));
        NumeroFollow.setText(String.valueOf(usuario.getContador_seguidos()));
        NumeroArmarios.setText(String.valueOf(usuario.getContador_armarios()));
        //los armarios sed erstablecen en funcion de la lista que se obtiene en recuperarArmariosUsuario()
    }

    private void setupViewPager(ViewPager viewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentVerPerfil(), "Ver Perfil");
        adapter.addFragment(new FragmentVerArmarios(), "Ver Armarios");
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
    //mostrar foto de perfil
    public void mostrarFotoPerfil(ImageView profileImageView) {
        if (apiService != null) {
            Call<RespuestaInsertarUsuario> call = apiService.getUsuarioById(1); // Cambia el 1 por el ID del usuario actual
            call.enqueue(new Callback<RespuestaInsertarUsuario>() {
                @Override
                public void onResponse(Call<RespuestaInsertarUsuario> call, Response<RespuestaInsertarUsuario> response) {
                    if (response.isSuccessful()) {
                        RespuestaInsertarUsuario usuario = response.body();
                        if (usuario != null) { // Verificar que el objeto usuario no sea nulo
                            String fotoBase64 = usuario.getFotoUsuario();
                            Log.d("FotoBase64", "" +fotoBase64); // Agrega este log para ver la fotoBase64
                            if (fotoBase64 != null && !fotoBase64.isEmpty()) {
                                // Decodificar la imagen desde Base64
                                byte[] decodedBytes = Base64.decode(fotoBase64, Base64.DEFAULT);
                                Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

                                // Mostrar la imagen decodificada en el ImageView
                                profileImageView.setImageBitmap(decodedBitmap);
                            }
                        } else {
                            Log.e("mostrarFotoPerfil", "El objeto usuario es nulo");
                        }
                    } else {
                        // Manejar la respuesta de error
                        Log.e("mostrarFotoPerfil", "Respuesta no exitosa: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<RespuestaInsertarUsuario> call, Throwable t) {
                    // Manejar el fallo en la llamada a la API
                    Log.e("mostrarFotoPerfil", "Error en la llamada a la API: " + t.getMessage());
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
                    // Toast.makeText(Registro.this,"INSERTYADO", Toast.LENGTH_SHORT).show();
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

        ImageView profileImageDialog = dialog.findViewById(R.id.imagenDialog);
        EditText usernameDialog = dialog.findViewById(R.id.userDialog);
        usernameDialog.setHint("@" + usuario.getUserName());
        EditText nameDialog = dialog.findViewById(R.id.nameDialog);
        nameDialog.setHint(usuario.getNombre());
        Button seguirdoDialogButton = dialog.findViewById(R.id.contraseñaDialog);

        //a ver como hacemos el tema de la foto, podemos poner
        // que al darle a cambiar foto salte la camarao que seleccione
        // una de galeria.
        //por otro lado, el post se hará cuando se cierr el Dialog?
        //ponemos un boton mas dentro del dialog?


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

        seguirdoDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), EditUser.class);
                startActivity(i);
            }
        });
    }



}

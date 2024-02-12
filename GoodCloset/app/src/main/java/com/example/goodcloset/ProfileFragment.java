package com.example.goodcloset;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaGetArmariosDeUsuario;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.Retrofit.SingletonUser;
import com.example.goodcloset.modelos.ArmarioModelo;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  ProfileFragment extends Fragment {
    TextView nombreUser, NumeroFollower, NumeroArmarios;//numero armarios se tendria que cargar en el onResponse.con un .size() de la lista recuperada.
    ApiService apiService;
    Button newArmarioButton;
<<<<<<< HEAD
    RecyclerView recyclerView;
    List<ArmarioModelo> armariosList;
=======

    private TabLayout tabLayout;
    private ViewPager viewPager;
>>>>>>> 9573c1effbcf0e8ddbb8a1ae337fbd63fbcefd15

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        // Obtener referencia al TabLayout desde el diseño inflado
        TabLayout tabLayout = rootView.findViewById(R.id.tabLayout);
        Button newArmarioButton = rootView.findViewById(R.id.NewArmarioButton);
        newArmarioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNewArmarioDialog();
            }
        });

        // Agregar pestañas al TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Sección 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Sección 2"));

        apiService = ApiClient.getInstance().getApiService();
<<<<<<< HEAD
        RespuestaInsertarUsuario usuario = SingletonUser.getInstance().getUsuario();
        Log.d("test",usuario.toString());

        Log.d("test","seguidores ->" + usuario.getContadorSeguidores() + "// usrname -> " +usuario.getUserName());
        nombreUser = rootView.findViewById(R.id.usernameTextView);
        NumeroFollower = rootView.findViewById(R.id.followersTextView);
        rootView.findViewById(R.id.tabLayout);
        establecerDatosDelUsuarioEnLaVista(usuario);
        recuperarArmariosUsuario(apiService,usuario);
=======
        recuperarArmariosUsuario(apiService);

        tabLayout = rootView.findViewById(R.id.tabLayout);
        viewPager = rootView.findViewById(R.id.viewPager);

        recyclerView = rootView.findViewById(R.id.recicledViewPerfil);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
>>>>>>> 9573c1effbcf0e8ddbb8a1ae337fbd63fbcefd15
        //creo que tendremos que usar un live data para manejaar el tema de los armarios y eso. lo mismo pasara con los datos del home y de "par ti" en tal caso.


        return rootView;
    }

<<<<<<< HEAD
    private void establecerDatosDelUsuarioEnLaVista(RespuestaInsertarUsuario usuario) {
       nombreUser.setText("@"+usuario.getUserName());
       NumeroFollower.setText("Seguidores: " + String.valueOf(usuario.getContadorSeguidores()));
        //los armarios sed erstablecen en funcion de la lista que se obtiene en recuperarArmariosUsuario()
    }

    private void recuperarArmariosUsuario(ApiService apiService, RespuestaInsertarUsuario usuario) {

=======
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


    private void recuperarArmariosUsuario(ApiService apiService) {
        RespuestaInsertarUsuario usuario = SingletonUser.getInstance().getUsuario();
>>>>>>> 9573c1effbcf0e8ddbb8a1ae337fbd63fbcefd15
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
            public void onClick(DialogInterface dialogInterface, int i) {
                String armarioName = input.getText().toString();
                Log.d("Nuevo Armario", "Nombre del Armario: " + armarioName);

                ArmarioModelo armario = new ArmarioModelo();
                RespuestaInsertarUsuario usuario = SingletonUser.getInstance().getUsuario();//para obtener el id de nuestro ususario en accion
                armario.setId_propietario(usuario.getId());
                armario.setNombre_armario(armarioName);//armario nuevo solo cn nombre:
///////////////////////

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

///////////////////////////
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
}

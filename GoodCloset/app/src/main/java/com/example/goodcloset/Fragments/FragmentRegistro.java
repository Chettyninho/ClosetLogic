package com.example.goodcloset.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goodcloset.LogIn;
import com.example.goodcloset.MainActivity;
import com.example.goodcloset.R;
import com.example.goodcloset.Registro;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.modelos.UsuarioModelo;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRegistro#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class FragmentRegistro extends Fragment {

    ImageView imgbg;
    EditText txtView,name,surname,email,username,password;
    Button button;
    LinearLayout ly;

    private UsuarioModelo usuarioAInsertar;
    private ApiClient apiClient;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_registro, container, false);
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        name = rootView.findViewById(R.id.name);
        surname = rootView.findViewById(R.id.apellido);
        email = rootView.findViewById(R.id.email);
        username = rootView.findViewById(R.id.username);
        password = rootView.findViewById(R.id.password);
        button = rootView.findViewById(R.id.enviar);

        // Inicializar ApiClient con la URL base de tu API (?)
        apiClient = ApiClient.getInstance();

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                prepararYInsertarUsuario();

                Intent i = new Intent(getContext(), MainActivity.class);
                //de esta manera compartimos la respuesta(el usuario que entra) con la actividad main activity:
                //i.putExtra("usuarioRegistrado",respuesta);//relacion clave-valor entre el String y el objeto
                startActivity(i);
                requireActivity().finish();

            }

        });

        return rootView;
    }

    private void prepararYInsertarUsuario() {
        //int idUsuario = Integer.parseInt(editTextid.getText().toString());
        String nombre = name.getText().toString();
        String aepllid = surname.getText().toString();
        String Email = email.getText().toString();
        String userName = username.getText().toString();
        String contraseñaSinHassear = password.getText().toString();

        usuarioAInsertar = new UsuarioModelo(nombre, aepllid, Email, userName, contraseñaSinHassear);
        Log.d("!!!!!!!!!!!!nombre de usuario: " + usuarioAInsertar.getUserName(),"contraseña: " +usuarioAInsertar.getContraseñaSinHassear());
        llamarRetrofitInsertarUsuario(usuarioAInsertar);
    }
    private void llamarRetrofitInsertarUsuario(UsuarioModelo usuarioAInsertar) {
        ApiService apiService = ApiClient.getInstance().getApiService();
        Log.e("NOMBRE:","VALOR:" + usuarioAInsertar.getNombre());
        if (apiService != null) {
            Call<RespuestaInsertarUsuario> call = apiService.insertarUsuario(usuarioAInsertar);
            String url = call.request().url().toString();

            Log.d("URL de la llamada", url);
            call.enqueue(new Callback<RespuestaInsertarUsuario>() {

                @Override
                public void onResponse(Call<RespuestaInsertarUsuario> call, Response<RespuestaInsertarUsuario> response) {
                    Log.e("antes del if de succesfull", "Código de error: " + response.code());

                    if (response.isSuccessful()) {
                        RespuestaInsertarUsuario respuesta = response.body();
                        Log.d("JSON de la respuesta", new Gson().toJson(respuesta));

                        Log.d("Respuesta Exitosa", String.valueOf(respuesta));
                        Toast.makeText(getContext(), "INSERTYADO", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("Respuesta Erronea", "Código de error: " + response.code());
                    }

                }

                @Override
                public void onFailure(Call<RespuestaInsertarUsuario> call, Throwable t) {
                    Log.e("Respuesta Erronea", "Código de error: " + t.getMessage());
                }

            });
            // Toast.makeText(Registro.this,"INSERTYADO", Toast.LENGTH_SHORT).show();
        } else {
            // Mostrar un Toast indicando que apiService es null
            Toast.makeText(getContext(),"Error: apiService es null", Toast.LENGTH_SHORT).show();
        }

    }

}
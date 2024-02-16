package com.example.goodcloset.Fragments;



import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goodcloset.MainActivity;
import com.example.goodcloset.R;
import com.example.goodcloset.Retrofit.ApiClient;
import com.example.goodcloset.Retrofit.ApiService;
import com.example.goodcloset.Retrofit.Respuestas.RespuestaInsertarUsuario;
import com.example.goodcloset.Retrofit.SingletonUser;
import com.example.goodcloset.modelos.UsuarioModelo;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLogIn#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLogIn extends Fragment {

    ImageView imgbg;
    EditText username,paswordsinHassear;
    Button enviar;
    private TextInputLayout textInputLayout;
    private EditText passwordEditText;
    private ImageButton visibilityButton;

    private TextView forgotPassword, goRegister;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_log_in, container, false);

        super.onCreate(savedInstanceState);
        //imgbg = rootView.findViewById(R.id.loginbg);
        //imgbg.animate().translationY(BIND_ABOVE_CLIENT).setDuration(1000).setStartDelay(3000);

        username = rootView.findViewById(R.id.userLogin);
        paswordsinHassear = rootView.findViewById(R.id.pswdLogin);
        forgotPassword = rootView.findViewById(R.id.forgotPassword);
        enviar = rootView.findViewById(R.id.enviarLogin);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepararInserccion();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showForgotPassword();
            }
        });

        //cambiar a registro cuando
        /*TextView signUpTextView = rootView.findViewById(R.id.login_signuptv);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la transacción del fragmento de registro
                FragmentRegistro fragmentRegistro = new FragmentRegistro();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragmentRegistro);
                transaction.addToBackStack(null);  // Agregar transacción a la pila de retroceso
                transaction.commit();
            }
        });*/

        textInputLayout = rootView.findViewById(R.id.textInputLayout);
        passwordEditText = rootView.findViewById(R.id.pswdLogin);
        visibilityButton = rootView.findViewById(R.id.togglePasswordVisibility);

        visibilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambiar la visibilidad del campo de contraseña
                if (passwordEditText.getTransformationMethod() != null) {
                    // Si la contraseña es visible, la oculta
                    passwordEditText.setTransformationMethod(null);
                    visibilityButton.setImageResource(R.drawable.baseline_visibility_24);
                } else {
                    // Si la contraseña está oculta, la hace visible
                    passwordEditText.setTransformationMethod(new PasswordTransformationMethod());
                    visibilityButton.setImageResource(R.drawable.baseline_visibility_off_24);
                }
            }
        });

        return rootView;
    }

    private void prepararInserccion() {

        String userName = username.getText().toString();
        String contraseñaSinHassear = paswordsinHassear.getText().toString();
        //Log.d("contraseña: ","->" + contraseñaSinHassear);

        UsuarioModelo usuarioAInsertar = new UsuarioModelo(userName, contraseñaSinHassear);
        entrar(usuarioAInsertar);
    }

    private void entrar(UsuarioModelo usuarioAInsertar) {
        ApiService apiService = ApiClient.getInstance().getApiService();
        if (apiService != null) {
            Call<RespuestaInsertarUsuario> call = apiService.login(usuarioAInsertar);

            call.enqueue(new Callback<RespuestaInsertarUsuario>() {

                @Override
                public void onResponse(Call<RespuestaInsertarUsuario> call, Response<RespuestaInsertarUsuario> response) {

                    if (response.isSuccessful()) {
                        RespuestaInsertarUsuario respuesta = response.body();

                        //Log.d("salt"," : " + Arrays.toString(respuesta.getSalt());
                        //String userNameCheck = respuesta.getUserName(); // Obtén el usuario de la respuesta
                        Log.e("ALL RESPUESTA", "username" + respuesta.getUserName() +
                                ", // pswdd: " + respuesta.getContraseñaSinHassear());

                        if (respuesta.getSalt() != null) {
                            Log.e("dentrodelIF:",": " + respuesta.getSalt());
                            respuesta.setSaltReal(Arrays.toString(respuesta.getSalt()).getBytes());
                            Log.d("salt", " : " + Arrays.toString(respuesta.getSalt()));
                            //crear el singleton del usuario recibido.
                            SingletonUser.getInstance().setUsuario(respuesta);

                            Intent i = new Intent(getContext(), MainActivity.class);
                            //de esta manera compartimos la respuesta(el usuario que entra) con la actividad main activity:
                            //i.putExtra("usuarioRegistrado",respuesta);//relacion clave-valor entre el String y el objeto
                            startActivity(i);
                            requireActivity().finish();

                            // Resto del código...
                        }else {
                            Log.d("surraza","SI BYTE ES NULL, ES DECIR NO COINCIDE EL USER.");

                            //Toast.makeText(LogIn.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                        }
                    }
                }

                @Override
                public void onFailure(Call<RespuestaInsertarUsuario> call, Throwable t) {
                    Log.e("Error en la llamada", "Tipo de excepción: " + t.getClass().getSimpleName());
                    if (t instanceof JsonSyntaxException) {
                        // Imprimir información adicional sobre el JSON en caso de JsonSyntaxException
                        Log.e("Error en la llamada", "Cuerpo de la respuesta JSON: " + ((JsonSyntaxException) t).getMessage());
                        // Imprimir información adicional sobre el JSON en caso de JsonSyntaxException
                        String jsonString = getJsonStringFromErrorBody(call, t);
                        Log.e("JSON", "Cuerpo de la respuesta JSON: " + jsonString);

                        if (jsonString != null) {
                            // Utilizar Gson para deserializar el JSON en un objeto RespuestaInsertarUsuario
                            Gson gson = new Gson();
                            RespuestaInsertarUsuario respuesta = gson.fromJson(jsonString, RespuestaInsertarUsuario.class);

                            // Imprimir cada dato del objeto RespuestaInsertarUsuario
                            Log.d("Datos del JSON", "ID: " + respuesta.getId());
                            Log.d("Datos del JSON", "Nombre: " + respuesta.getNombre());
                            Log.d("Datos del JSON", "Apellido: " + respuesta.getSurname());
                            // Añadir más líneas según los campos que tengas en la clase RespuestaInsertarUsuario
                        }
                    }
                }
            });
        } else {
            // Mostrar un Toast indicando que apiService es null, lo normal es que esto nucna sea null
            Toast.makeText(getContext(),"Error: apiService es null", Toast.LENGTH_SHORT).show();
        }

    }
    private String getJsonStringFromErrorBody(Call<RespuestaInsertarUsuario> call, Throwable t) {
        if (t instanceof HttpException) {
            ResponseBody errorBody = ((HttpException) t).response().errorBody();
            try {
                return errorBody != null ? errorBody.string() : null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void showForgotPassword() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //Cambia el texto que haya para clickar
        builder.setTitle("¿Olvidaste tu contraseña?");
        //Una vez clickas sale una alerta, pues te es el mensaje
        builder.setMessage("Introduce tu dirección de correo electrónico para restablecer la contraseña:");

        builder.setPositiveButton("Cambiar contraseña", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Aquí puedes manejar la lógica de enviar el correo electrónico o realizar cualquier otra acción necesaria
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }



}
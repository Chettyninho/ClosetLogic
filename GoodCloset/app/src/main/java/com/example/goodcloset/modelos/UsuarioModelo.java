package com.example.goodcloset.modelos;

import com.google.gson.annotations.SerializedName;

public class UsuarioModelo {

    @SerializedName("id")
    private int id;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("surname")
    private String surname;

    @SerializedName("email")
    private String email;

    @SerializedName("userName")
    private String userName;

    @SerializedName("contador_seguidores")
    private int contadorSeguidores;

    @SerializedName("privado")
    private boolean privado;

    @SerializedName("fechaNacimiento")
    private String fechaNacimiento;

    @SerializedName("hashContraseña")
    private String hashContraseña;

    @SerializedName("salt")
    private byte[] salt;

    @SerializedName("fotoEnBase64")
    private String fotoUsuario;  // Dependiendo del formato de tu API, ajusta el tipo de dato

    // Constructor, getters y setters

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public int getContadorSeguidores() {
        return contadorSeguidores;
    }

    public boolean isPrivado() {
        return privado;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getHashContraseña() {
        return hashContraseña;
    }

    public byte[] getSalt() {
        return salt;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }
}

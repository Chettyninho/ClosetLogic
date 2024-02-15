package com.example.goodcloset.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.Base64;

public class Usuario {

    @SerializedName("id")
    private Integer id;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("surname")
    private String surname;

    @SerializedName("email")
    private String email;

    @SerializedName("username")
    private String username;

    @SerializedName("contador_seguidores")
    private Integer contador_seguidores;

    @SerializedName("fechaNacimiento")
    private String fechaNacimiento;

    @SerializedName("password")
    private String password;

    @SerializedName("salt")
    private String salt;

    @SerializedName("fotoUsuario")
    private String fotoUsuario;

    private byte[] saltReal;
    public void setSaltReal(byte[] saltReal) {
        this.saltReal = saltReal;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getContraseñaSinHassear() {
        return password;
    }

    public void setContraseñaSinHassear(String contraseñaSinHassear) {
        this.password = contraseñaSinHassear;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getSalt() {
        if (salt != null) {
            return Base64.getDecoder().decode(salt); //da fallo pero funciona y recoje el array de bytes correctamente.
        }
        return null;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getContador_seguidores() {
        return contador_seguidores;
    }

    public void setContador_seguidores(Integer contador_seguidores) {
        this.contador_seguidores = contador_seguidores;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }

    public Usuario() {
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Usuario(String nombre, String surname, String email, String userName, String contraseñaSinHassear) {
        this.nombre = nombre;
        this.surname = surname;
        this.email = email;
        this.username = userName;

        this.password = contraseñaSinHassear;
    }


}

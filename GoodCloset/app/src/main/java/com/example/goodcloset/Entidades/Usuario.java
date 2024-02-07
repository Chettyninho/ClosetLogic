package com.example.goodcloset.Entidades;

import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("id")
    private Integer id;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("surname")
    private String surname;

    @SerializedName("email")
    private String email;

    @SerializedName("user_name")
    private String user_name;

    @SerializedName("fechaNacimiento")
    private String fechaNacimiento;

    @SerializedName("contraseñaSinHassear")
    private String contraseñaSinHassear;

    @SerializedName("salt")
    private byte[] salt;

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
        return user_name;
    }

    public void setUserName(String userName) {
        this.user_name = userName;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getContraseñaSinHassear() {
        return contraseñaSinHassear;
    }

    public void setContraseñaSinHassear(String contraseñaSinHassear) {
        this.contraseñaSinHassear = contraseñaSinHassear;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public Usuario() {
    }

    public Usuario(String nombre, String surname, String email, String userName, String contraseñaSinHassear) {
        this.nombre = nombre;
        this.surname = surname;
        this.email = email;
        this.user_name = userName;

        this.contraseñaSinHassear = contraseñaSinHassear;
    }
}

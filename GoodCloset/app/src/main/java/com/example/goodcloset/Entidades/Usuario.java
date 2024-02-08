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

    @SerializedName("username")
    private String username;



    @SerializedName("fechaNacimiento")
    private String fechaNacimiento;

    @SerializedName("password")
    private String password;


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

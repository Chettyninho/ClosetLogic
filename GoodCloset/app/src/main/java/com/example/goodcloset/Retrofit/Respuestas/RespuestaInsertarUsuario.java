package com.example.goodcloset.Retrofit.Respuestas;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;

public class RespuestaInsertarUsuario implements Serializable {

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
    @SerializedName("contador_seguidos")
    private int contador_seguidos;
    @SerializedName("contador_armarios")
    private int contador_armarios;
    @SerializedName("fechaNacimiento")
    private String fechaNacimiento;
    @SerializedName("hashContraseña")
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

    public int getContador_seguidos() {
        return contador_seguidos;
    }

    public void setContador_seguidos(int contador_seguidos) {
        this.contador_seguidos = contador_seguidos;
    }

    public int getContador_armarios() {
        return contador_armarios;
    }

    public void setContador_armarios(int contador_armarios) {
        this.contador_armarios = contador_armarios;
    }

    public RespuestaInsertarUsuario(Integer id, String nombre, String surname, String email, String username, Integer contador_seguidores, int contador_seguidos, int contador_armarios, String fotoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.contador_seguidores = contador_seguidores;
        this.contador_seguidos = contador_seguidos;
        this.contador_armarios = contador_armarios;
        this.fotoUsuario = fotoUsuario;
    }

    @Override
    public String toString() {
        return "RespuestaInsertarUsuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", contador_seguidores=" + contador_seguidores +
                ", contador_seguidos=" + contador_seguidos +
                ", contador_armarios=" + contador_armarios +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", fotoUsuario='" + fotoUsuario + '\'' +
                '}';
    }

}

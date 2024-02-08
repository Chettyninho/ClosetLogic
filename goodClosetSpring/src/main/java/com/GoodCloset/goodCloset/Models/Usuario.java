package com.GoodCloset.goodCloset.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Usuario {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "user_name")
    private String username;
    @Column(name = "contador_seguidores")
    private Integer contador_seguidores;
    private Boolean privado;
    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;
    @Column(name = "hash_contrasena")
    private String password;
    private byte[] salt;
    private byte[] foto_usuario;

    //@OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Armario> armarios;
}

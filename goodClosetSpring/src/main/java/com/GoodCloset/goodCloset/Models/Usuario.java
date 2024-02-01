package com.GoodCloset.goodCloset.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String email;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "contador_seguidores")
    private Integer contadorSeguidores;
    private Boolean privado;
    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;
    @Column(name = "hash_contrasena")
    private String hashContraseña;
    private byte[] salt;
}

package com.GoodCloset.goodCloset.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_like_armario")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UsuarioLikeArmario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_armario")
    private Armario armario;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}

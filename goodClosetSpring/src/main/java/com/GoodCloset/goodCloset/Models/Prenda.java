package com.GoodCloset.goodCloset.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Prenda")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Prenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "marca")
    private String marca;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "talla")
    private String talla;

    @Column(name = "color")
    private String color;

    @Column(name = "enlace_web")
    private String enlaceWeb;

    @Column(name = "foto_prenda")
    private String fotoEnBase64;

    // Relación muchos a muchos con Outfit
    @JsonIgnore
    @ManyToMany(mappedBy = "prendas")
    private List<Outfit> outfits;
}

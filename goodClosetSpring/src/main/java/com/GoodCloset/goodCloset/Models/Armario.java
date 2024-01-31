package com.GoodCloset.goodCloset.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;  // Cambiado a la anotación correcta
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Armario")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Armario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int contador_de_outfits;
    private int contador_de_likes;
}

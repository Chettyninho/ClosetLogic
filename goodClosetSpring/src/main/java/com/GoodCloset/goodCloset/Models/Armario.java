package com.GoodCloset.goodCloset.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private String nombre;
    private int contador_de_outfits;
    private int contador_de_likes;
    private int id_propietario;


    //@ManyToOne
    //@JoinColumn(name = "id_propietario")
    //private Usuario propietario;

    //@ManyToMany
    //@JoinTable(
      //      name = "armario_outfit",
        //    joinColumns = @JoinColumn(name = "id_armario"),
          //  inverseJoinColumns = @JoinColumn(name = "id_outfit"))
    //private List<Outfit> outfits;
//    @ManyToMany
//    private List<Outfit> listaOutfits;
}

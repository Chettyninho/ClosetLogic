package com.GoodCloset.goodCloset.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Outfit")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Outfit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String Nombre;
    private String descripcion;
    private String fecha_cracion;

    //private List<Prenda> listaPrendas;

    //@ManyToMany(mappedBy = "outfits")
    //private List<Armario> armarios;

//    @ManyToMany
//    @JoinTable(
//            name = "prenda_outfit",
//            joinColumns = @JoinColumn(name = "id_outfit"),
//            inverseJoinColumns = @JoinColumn(name = "id_prenda"))
//    private List<Prenda> prendas;

//estas relaciones son muy impirtantes.
}

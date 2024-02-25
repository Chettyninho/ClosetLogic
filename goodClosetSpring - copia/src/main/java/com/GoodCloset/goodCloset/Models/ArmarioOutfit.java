package com.GoodCloset.goodCloset.Models;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "armario_outfit")
public class ArmarioOutfit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_armario")
    private Armario armario;

    @ManyToOne
    @JoinColumn(name = "id_outfit")
    private Outfit outfit;




    public ArmarioOutfit(Armario armario, Outfit outFit) {
        this.armario = armario;
        this.outfit =outFit;
    }
}

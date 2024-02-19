package com.GoodCloset.goodCloset.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Prenda_Outfit")

public class Prenda_Outfit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_prenda")
    private Prenda prenda;

    @ManyToOne
    @JoinColumn(name = "id_outfit")
    private Outfit outfit;

    public Prenda_Outfit(Prenda prenda, Outfit outfit) {
        this.prenda = prenda;
        this.outfit = outfit;
    }
}

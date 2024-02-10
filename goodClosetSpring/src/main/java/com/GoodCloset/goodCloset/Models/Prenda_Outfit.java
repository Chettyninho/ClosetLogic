package com.GoodCloset.goodCloset.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Prenda_Outfit")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Prenda_Outfit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @ManyToOne
//    @JoinColumn(name = "id_prenda")
//    private Prenda prenda;

    @ManyToOne
    @JoinColumn(name = "id_outfit")
    private Outfit outfit;

}

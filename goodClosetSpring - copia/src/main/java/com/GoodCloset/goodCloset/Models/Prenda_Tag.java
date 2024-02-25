package com.GoodCloset.goodCloset.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Prenda_Tag")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Prenda_Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @ManyToOne
//    @JoinColumn(name = "id_prenda")
//    private Prenda prenda;

    @ManyToOne
    @JoinColumn(name = "id_tag")
    private Tag tag;
}

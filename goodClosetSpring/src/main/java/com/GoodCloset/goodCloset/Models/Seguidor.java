package com.GoodCloset.goodCloset.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Seguidor")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Seguidor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_seguidor")
    private Usuario seguidor;

    @ManyToOne
    @JoinColumn(name = "id_seguido")
    private Usuario seguido;
}

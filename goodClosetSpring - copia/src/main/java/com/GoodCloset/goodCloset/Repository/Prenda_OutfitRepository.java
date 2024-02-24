package com.GoodCloset.goodCloset.Repository;

import com.GoodCloset.goodCloset.Models.Prenda_Outfit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Prenda_OutfitRepository extends JpaRepository<Prenda_Outfit,Integer> {
    //List<Prenda_Outfit> findAll(); este metodo realmene no hace fata porque viene predefinido en JpaRepository
}

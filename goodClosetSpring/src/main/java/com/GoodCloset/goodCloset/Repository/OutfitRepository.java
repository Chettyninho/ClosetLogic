package com.GoodCloset.goodCloset.Repository;

import com.GoodCloset.goodCloset.Models.Outfit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutfitRepository extends JpaRepository<Outfit,Integer> {
//List<Outfit> findAll(); este metodo realmene no hace fata porque viene predefinido en JpaRepository
}

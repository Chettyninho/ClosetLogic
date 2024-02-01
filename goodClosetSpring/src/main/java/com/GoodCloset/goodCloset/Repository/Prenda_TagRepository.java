package com.GoodCloset.goodCloset.Repository;

import com.GoodCloset.goodCloset.Models.Prenda_Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Prenda_TagRepository extends JpaRepository<Prenda_Tag,Integer> {
    //List<Prenda_Tag> findAll(); este metodo realmene no hace fata porque viene predefinido en JpaRepository
}

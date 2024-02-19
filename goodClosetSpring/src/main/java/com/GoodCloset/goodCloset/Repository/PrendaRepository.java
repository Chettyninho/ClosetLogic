package com.GoodCloset.goodCloset.Repository;

import com.GoodCloset.goodCloset.Models.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrendaRepository extends JpaRepository<Prenda,Integer> {
    List<Prenda> findTop3ByOrderByIdDesc();
}

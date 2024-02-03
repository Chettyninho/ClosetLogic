package com.GoodCloset.goodCloset.Repository;

import com.GoodCloset.goodCloset.Models.Seguidor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeguidorRepository extends JpaRepository<Seguidor,Integer> {
    List<Seguidor> findBySeguidorId(Integer idSeguidor);
    List<Seguidor> findBySeguidoId(Integer idSeguido);
}

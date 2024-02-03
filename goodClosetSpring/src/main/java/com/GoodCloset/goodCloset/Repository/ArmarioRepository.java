package com.GoodCloset.goodCloset.Repository;

import com.GoodCloset.goodCloset.Models.Armario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArmarioRepository extends JpaRepository<Armario, Integer> {

    //@Query("SELECT DISTINCT a FROM Armario a " +
      //      "LEFT JOIN FETCH a.outfits o " +
        //    "WHERE a.propietario.id = :idPropietario")
    //List<Armario> findAllByPropietario(@Param("idPropietario") Integer idPropietario);

}

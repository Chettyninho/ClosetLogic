package com.GoodCloset.goodCloset.Repository;

import com.GoodCloset.goodCloset.Models.Armario;
import com.GoodCloset.goodCloset.Models.Outfit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArmarioRepository extends JpaRepository<Armario, Integer> {

    //@Query("SELECT DISTINCT a FROM Armario a " +
      //      "LEFT JOIN FETCH a.outfits o " +
        //    "WHERE a.propietario.id = :idPropietario")
    //List<Armario> findAllByPropietario(@Param("idPropietario") Integer idPropietario);


    @Query("SELECT ao.outfit FROM ArmarioOutfit ao WHERE ao.armario.id = :armarioId")
    List<Outfit> findOutfitsByArmarioId(@Param("armarioId") Integer armarioId);

    @Query("SELECT a FROM Armario a WHERE a.propietario.id = :id")
    List<Armario> findArmariosByUserId(@Param("id") Integer id);
}

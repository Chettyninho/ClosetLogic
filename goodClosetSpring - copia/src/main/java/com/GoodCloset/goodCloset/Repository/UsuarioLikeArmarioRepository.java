package com.GoodCloset.goodCloset.Repository;

import com.GoodCloset.goodCloset.Models.Usuario;
import com.GoodCloset.goodCloset.Models.UsuarioLikeArmario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioLikeArmarioRepository extends JpaRepository<UsuarioLikeArmario,Integer> {

}

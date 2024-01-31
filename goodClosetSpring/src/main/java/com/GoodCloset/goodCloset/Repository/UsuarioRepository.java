package com.GoodCloset.goodCloset.Repository;

import com.GoodCloset.goodCloset.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    List<Usuario> findAll();
}

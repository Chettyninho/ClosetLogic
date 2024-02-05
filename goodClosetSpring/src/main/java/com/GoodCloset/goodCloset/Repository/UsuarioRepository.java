package com.GoodCloset.goodCloset.Repository;

import com.GoodCloset.goodCloset.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    //List<Usuario> findAll(); este metodo realmene no hace fata porque viene predefinido en JpaRepository

    public Usuario findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);

    /*@Query("SELECT * FROM Usuario u JOIN Seguidor s ON u.id = s.idSeguido WHERE s.idSeguidor = 1") // HE PUESTO PARA COMPROBAR SI FUNCIONABA Y LOS SACABA
    List<Usuario> findUsuariosSeguidosPorUsuario(@Param("idSeguidor") Integer idSeguidor);*/
}

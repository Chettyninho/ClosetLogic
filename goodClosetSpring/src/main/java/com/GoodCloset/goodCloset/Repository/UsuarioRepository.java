package com.GoodCloset.goodCloset.Repository;

import com.GoodCloset.goodCloset.Models.Seguidor;
import com.GoodCloset.goodCloset.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    //List<Usuario> findAll(); este metodo realmene no hace fata porque viene predefinido en JpaRepository

    public List<Usuario> findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Query("SELECT s.seguido FROM Seguidor s WHERE s.seguidor.id = :idSeguidor") //SACA TODOS LOS USUARIOS A LOS QUE SIGUE EL USUARIO.
    List<Usuario> findUsuariosSeguidosPorUsuario(@Param("idSeguidor") Integer idSeguidor);


}

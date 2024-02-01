package com.GoodCloset.goodCloset.Repository;

import com.GoodCloset.goodCloset.Models.Seguidor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeguidorRepository extends JpaRepository<Seguidor,Integer> {
//Aqui no se como ira realmente, seria obtener todos de un solo id, por que se supone que seria o el que estas mirando o el tuyo propio
}

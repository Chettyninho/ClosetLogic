package com.GoodCloset.goodCloset.Controller;

import com.GoodCloset.goodCloset.Models.Seguidor;
import com.GoodCloset.goodCloset.Models.Tag;
import com.GoodCloset.goodCloset.Service.SeguidorService;
import com.GoodCloset.goodCloset.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seguidor")
public class SeguidorController {
    @Autowired//para conectar directamente con el Repositoryç
    private SeguidorService seguidorService;

    @GetMapping("/{id}/seguidores") //quien sigue al id
    public List<Seguidor> obtenerSeguidores(@PathVariable Integer id) {
        return seguidorService.obtenerSeguidores(id);
    }

                    /// estas funciones vendran bien para mostrar tambien por ejkemplo las fotos en el home de los usuarios a los que sigues///
    @GetMapping("/{id}/seguidos") //a quien sigue el id
    public List<Seguidor> obtenerSeguidos(@PathVariable Integer id) {
        return seguidorService.obtenerSeguidos(id);
    }
}

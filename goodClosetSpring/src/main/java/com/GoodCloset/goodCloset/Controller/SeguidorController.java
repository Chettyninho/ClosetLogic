package com.GoodCloset.goodCloset.Controller;

import com.GoodCloset.goodCloset.Models.Seguidor;
import com.GoodCloset.goodCloset.Models.Tag;
import com.GoodCloset.goodCloset.Service.SeguidorService;
import com.GoodCloset.goodCloset.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seguidor")
public class SeguidorController {
    @Autowired//para conectar directamente con el Repositoryç
    private SeguidorService seguidorService;

    //@GetMapping("/all")
    //public List<Seguidor> getAll(){
        //return seguidorService.getSeguidores?();
       //aqui no sabemos que poner
    //}
}

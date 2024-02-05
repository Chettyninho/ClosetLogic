package com.GoodCloset.goodCloset.Controller;

import com.GoodCloset.goodCloset.Models.Prenda;
import com.GoodCloset.goodCloset.Models.Usuario;
import com.GoodCloset.goodCloset.Repository.PrendaRepository;
import com.GoodCloset.goodCloset.Service.PrendaService;
import com.GoodCloset.goodCloset.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/prenda")
public class PrendaController {
    @Autowired//para conectar directamente con el Repository
    private PrendaService prendaService;

    @GetMapping("/all")
    public List<Prenda> getAllPrendas() {
        return prendaService.getAllPrendas();
    }





}


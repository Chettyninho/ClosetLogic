package com.GoodCloset.goodCloset.Controller;

import com.GoodCloset.goodCloset.Models.Armario;
import com.GoodCloset.goodCloset.Models.Usuario;
import com.GoodCloset.goodCloset.Service.ArmarioService;
import com.GoodCloset.goodCloset.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/armario")
public class ArmarioController {
@Autowired//para conectar directamente con el Repositoryç
    private ArmarioService armarioService;

    @GetMapping("/all")
    public List<Armario> getAllArmarios(){
        return armarioService.getAllArmarios();
    }
}

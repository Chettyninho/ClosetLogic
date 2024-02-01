package com.GoodCloset.goodCloset.Controller;

import com.GoodCloset.goodCloset.Models.Usuario;
import com.GoodCloset.goodCloset.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
@Autowired//para conectar directamente con el Repository
    private UsuarioService usuarioService;

    @GetMapping("/all")
    public List<Usuario> getAllUsuarios(){
        return usuarioService.getAllUsuarios();
    }

    @PostMapping("/saveUser")
    public Usuario saveUser(@RequestBody Usuario usuario){
        return usuarioService.saveUser(usuario);
    }
}

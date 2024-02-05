package com.GoodCloset.goodCloset.Controller;

import com.GoodCloset.goodCloset.Models.Usuario;
import com.GoodCloset.goodCloset.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/inicio")
public class HomeController {


    @Autowired
    UsuarioRepository uRepo;

    @PostMapping("/login")
    public Usuario loguear(@RequestBody Usuario usuario) {

        Usuario usuario1 = uRepo.findByUsernameAndPassword(usuario.getUsername(), usuario.getPassword());
        System.out.println(usuario1);
        Usuario usuario2 = new Usuario();
        if (usuario1 == null) {
            return usuario2;
        } else {
            return usuario1;
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registerUser(@RequestBody Usuario user) {
        if (uRepo.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("El nombre de usuario ya está en uso", HttpStatus.BAD_REQUEST);
        }

        uRepo.save(user); // Cambio aquí
        return new ResponseEntity<>("Usuario registrado con éxito", HttpStatus.OK);
    }

}

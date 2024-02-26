package com.GoodCloset.goodCloset.Controller;

import com.GoodCloset.goodCloset.Models.Usuario;
import com.GoodCloset.goodCloset.Repository.UsuarioRepository;
import com.GoodCloset.goodCloset.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;


@RestController
@RequestMapping("/inicio")
public class HomeController {

    @Autowired
    UsuarioRepository uRepo;


    @PostMapping("/login")
    public Usuario loguear(@RequestBody Usuario usuario) {
        List<Usuario> usuarios = uRepo.findAll();
        Usuario usuario2 = null;
        for (Usuario u : usuarios) {
            if(u.getPassword()!=null && u.getSalt()!=null){
                if (u.getUsername().equals(usuario.getUsername()) && u.getPassword().equals(comprobarContraseña(u,usuario.getPassword()))) {
                    usuario2 = u;
                    break;
                }else{
                    usuario2 = new Usuario();
                }
            }
        }
        return usuario2;
    }

    private String comprobarContraseña(Usuario usuario, String testPasword){
        byte[] salt = usuario.getSalt();
        String hashedPassword = UsuarioService.hashPassword(testPasword, salt);
        return hashedPassword;
    }


}

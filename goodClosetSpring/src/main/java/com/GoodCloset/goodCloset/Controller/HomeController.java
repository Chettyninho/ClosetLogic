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
        System.out.println("usuario a la llegada: " +usuario);

        List<Usuario> usuarios = uRepo.findAll();
        //System.out.println("\nListaDeUsuarios: " + usuarios);
        Usuario usuario2 = null;
        for (Usuario u : usuarios) {
            //System.out.println(u);
            if(u.getPassword()!=null && u.getSalt()!=null){
//                System.out.println("username: " +u.getUsername() + "\nPswd: " + u.getPassword());
//                System.out.println("Contraseña despues de hassear: " + comprobarContraseña(u,usuario.getPassword()));
                if (u.getUsername().equals(usuario.getUsername()) && u.getPassword().equals(comprobarContraseña(u,usuario.getPassword()))) {
                    usuario2 = u;
                    System.out.println("\n\n\nEl usuario que se devuelve SI coincide es: " + usuario2);
                    break;
                }else{
                    usuario2 = new Usuario();
                    System.out.println("\n\n\nEl usuario que se devuelvesi NO coincide es: " + usuario2);
                }
            }

        }
        System.out.println("usuario que se devuelve" + usuario2);
        return usuario2;
    }

    private String comprobarContraseña(Usuario usuario, String testPasword){
        byte[] salt = usuario.getSalt();
        String hashedPassword = UsuarioService.hashPassword(testPasword, salt);
        System.out.println("Usuario pswd: " + usuario.getPassword());
        System.out.println("Contraseña introducida hasseada: " + hashedPassword);
        return hashedPassword;
    }


//    @PostMapping("/registrar")
//    public ResponseEntity<String> registerUser(@RequestBody Usuario user) {
//        if (uRepo.existsByUsername(user.getUsername())) {
//            return new ResponseEntity<>("El nombre de usuario ya está en uso", HttpStatus.BAD_REQUEST);
//        }
//
//        uRepo.save(user); // Cambio aquí
//        return new ResponseEntity<>("Usuario registrado con éxito", HttpStatus.OK);
//    }

}

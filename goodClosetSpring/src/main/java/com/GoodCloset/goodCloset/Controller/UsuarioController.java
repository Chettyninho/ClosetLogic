package com.GoodCloset.goodCloset.Controller;

import com.GoodCloset.goodCloset.Models.Usuario;
import com.GoodCloset.goodCloset.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/{id_usuario}") //obtiene un solo usuario. no una lista, lo usaremos para el perfil, editar perfil... por ejemplo
    public Usuario obtenerSeguidor(@PathVariable Integer id_usuario) {
        return usuarioService.getUsuarioById(id_usuario);
    }

    @PostMapping("/saveUser") //hace tanto la funcion de insert como de update.
    public Usuario saveUser(@RequestBody Usuario usuario){
        return usuarioService.saveUser(usuario);
    }

    @GetMapping("/find-user-by-chain/{chain}")
    public List<Usuario> findByName(@PathVariable("chain") String chain){
        List<Usuario> allUsuarios = usuarioService.getAllUsuarios();
        List<Usuario> usuarioShearched = new ArrayList<Usuario>();

        for(Usuario user : allUsuarios){
            if(user.getUserName().startsWith(chain)){
                usuarioShearched.add(user);
            }
        }
        return usuarioShearched;
    }



/*@GetMapping("/seguidos/{idSeguidor}")
    public List<Usuario> obtenerUsuariosSeguidosPorUsuario(@PathVariable Integer idSeguidor) {
        return usuarioService.obtenerUsuariosSeguidosPorUsuario(idSeguidor);
 que carga la pantalla principal
}*/


}

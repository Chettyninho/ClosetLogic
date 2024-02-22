package com.GoodCloset.goodCloset.Controller;

import com.GoodCloset.goodCloset.Models.Seguidor;
import com.GoodCloset.goodCloset.Models.Usuario;
import com.GoodCloset.goodCloset.Service.ArmarioService;
import com.GoodCloset.goodCloset.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
@Autowired//para conectar directamente con el Repository
    private UsuarioService usuarioService;
    private final ArmarioService armarioService;

    public UsuarioController(ArmarioService armarioService) {
        this.armarioService = armarioService;
    }

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
        return usuarioService.comprobarYRegistrarUsuario(usuario);
    }

    @PostMapping("/saveUserProfileImage")
    public Usuario saveUserImage(@RequestBody Usuario usuario){
        return usuarioService.postImageProfile(usuario);
    }
    @PostMapping("/editUser")
    public Usuario editUser(@RequestBody Usuario usuario, @RequestParam String contraseñaAntigua, @RequestParam String contraseñaNueva){
        System.out.println("entra en ocntroller");
        return usuarioService.chkPasswordAndEdtUser(usuario,contraseñaAntigua, contraseñaNueva);
    }

    @GetMapping("/find-user-by-chain/{chain}")
    public List<Usuario> findByName(@PathVariable("chain") String chain){
        List<Usuario> allUsuarios = usuarioService.getAllUsuarios();
        List<Usuario> usuarioShearched = new ArrayList<Usuario>();

        for(Usuario user : allUsuarios){
            if(user.getUsername().startsWith(chain)){
                usuarioShearched.add(user);
            }
        }
        return usuarioShearched;
    }



@GetMapping("/seguidos/{idSeguidor}")
    public List<Usuario> obtenerUsuariosSeguidosPorUsuario(@PathVariable Integer idSeguidor) {
        return usuarioService.obtenerUsuariosSeguidosPorUsuario(idSeguidor);
}




@DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer idUsuario) {
        try {
            boolean deleted = usuarioService.deleteUser(idUsuario);
            if (deleted) {
                return ResponseEntity.ok("Usuario eliminado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el usuario: " + e.getMessage());
        }
    }


    @GetMapping(("/find-user-by-username/{username}"))
    public Usuario findByUsername(@PathVariable("username") String username){
        List<Usuario> allUsuarios = usuarioService.getAllUsuarios();
        Usuario usuarioByUsername = new Usuario();

        for(Usuario user : allUsuarios){
            if(Objects.equals(user.getUsername(), username)){
                usuarioByUsername = user;
            }
        }
        return usuarioByUsername;
    }


 @PostMapping("seguir/{id_seguido}/{id_seguidor}")
    public void follow4Follow(@PathVariable("id_seguido") Integer id_seguido, @PathVariable("id_seguidor")Integer id_seguidor){
        usuarioService.follow4Follow(id_seguido,id_seguidor);
    }

}

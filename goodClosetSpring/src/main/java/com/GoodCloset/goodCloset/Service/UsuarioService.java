package com.GoodCloset.goodCloset.Service;

import com.GoodCloset.goodCloset.Models.Usuario;
import com.GoodCloset.goodCloset.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }
    public Usuario getUsuarioById(Integer id){
        return usuarioRepository.findById(id).get();
    }

    public Usuario saveUser(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public boolean existsByUsername(String username) { return usuarioRepository.existsByUsername(username); }

    public void createUser(Usuario user) { usuarioRepository.save(user); }

/*public List<Usuario> obtenerUsuariosSeguidosPorUsuario(Integer idSeguidor) {
        return usuarioRepository.findUsuariosSeguidosPorUsuario(idSeguidor);
    }
*/


  public boolean deleteUser(Integer idUsuario) {
        if (usuarioRepository.existsById(idUsuario)) {
            usuarioRepository.deleteById(idUsuario);
            return true;
        }
        return false;
    }

    public byte[] getFotoUsuarioPorId(Integer id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            return usuarioOptional.get().getFotoUsuario();
        } else {
            return null;
        }
    }



}

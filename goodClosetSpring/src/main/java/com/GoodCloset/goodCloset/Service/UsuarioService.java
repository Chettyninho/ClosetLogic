package com.GoodCloset.goodCloset.Service;

import com.GoodCloset.goodCloset.Models.Usuario;
import com.GoodCloset.goodCloset.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}

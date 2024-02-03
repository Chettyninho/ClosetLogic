package com.GoodCloset.goodCloset.Service;

import com.GoodCloset.goodCloset.Models.Armario;
import com.GoodCloset.goodCloset.Models.Usuario;
import com.GoodCloset.goodCloset.Repository.ArmarioRepository;
import com.GoodCloset.goodCloset.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArmarioService {
    @Autowired
    private ArmarioRepository armarioRepository;

    public List<Armario> getAllArmarios(){
        return armarioRepository.findAll();
    }

   // public List<Armario> findAllArmariosByPropietario(Integer idPropietario) {
     //   return armarioRepository.findAllByPropietario(idPropietario);
    //}

}

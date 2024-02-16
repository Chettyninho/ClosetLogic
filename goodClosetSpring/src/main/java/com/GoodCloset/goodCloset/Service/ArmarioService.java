package com.GoodCloset.goodCloset.Service;

import com.GoodCloset.goodCloset.Models.Armario;
import com.GoodCloset.goodCloset.Models.Outfit;
import com.GoodCloset.goodCloset.Repository.ArmarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArmarioService {
    @Autowired
    private ArmarioRepository armarioRepository;

    public List<Armario> getAllArmarios(){
        return armarioRepository.findAll();
    }

   public Armario saveArmario(Armario armario){
       return armarioRepository.save(armario);
   }

    public List<Armario> findAllArmariosByPropietario(Integer idPropietario) {
        System.out.println(armarioRepository.findAll());
        System.out.println(armarioRepository.findArmariosByUserId(idPropietario));
        return armarioRepository.findArmariosByUserId(idPropietario);
        /*
        List<Armario> armariosTotales= armarioRepository.findAll();
        List<Armario> armarioPropietario =  new ArrayList<>();
        for (Armario a : armariosTotales){
            if (a.getId_propietario() == idPropietario) {
                armarioPropietario.add(a);
            }
        }
        return armarioPropietario;

         */
    }

    public List<Outfit> findOutfitsByArmarioId(Integer armarioId) {
        return armarioRepository.findOutfitsByArmarioId(armarioId);
    }

}

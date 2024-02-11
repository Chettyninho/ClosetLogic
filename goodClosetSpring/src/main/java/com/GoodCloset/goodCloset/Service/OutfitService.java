package com.GoodCloset.goodCloset.Service;

import com.GoodCloset.goodCloset.Models.Outfit;
import com.GoodCloset.goodCloset.Repository.OutfitRepository;
import com.GoodCloset.goodCloset.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutfitService {
    @Autowired
    private OutfitRepository outfitRepository;

    public List<Outfit> getAllOutfits(){
        return outfitRepository.findAll();
    }


//añade un outfit pasandole la lista de imagenes
    public Outfit nuevoOutfit(Integer idArmario, List<String> imagenesCapturadaBase64) {
        //aqui habra que gestionar la creacion o no del armario en funcion des
        Outfit outfit = new Outfit();
        return outfit;
    }
}

package com.GoodCloset.goodCloset.Service;

import com.GoodCloset.goodCloset.Models.Outfit;
import com.GoodCloset.goodCloset.Models.Prenda;
import com.GoodCloset.goodCloset.Repository.OutfitRepository;
import com.GoodCloset.goodCloset.Repository.PrendaRepository;
import com.GoodCloset.goodCloset.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutfitService {
    @Autowired
    private OutfitRepository outfitRepository;
    @Autowired
    private PrendaRepository prendaRepository;



    public List<Outfit> getAllOutfits(){
        return outfitRepository.findAll();
    }


//añade un outfit pasandole la lista de imagenes
    public Outfit nuevoOutfit(Integer idArmario, List<String> imagenesCapturadaBase64) {
        //1.creamos el outfit
        Outfit outfit = new Outfit();
        outfit.setNombre("test");
        outfit.setDescripcion("Outfit de prueba");
        outfitRepository.save(outfit);
        int i =0;
        for (String s : imagenesCapturadaBase64){

            Prenda prenda = new Prenda();
            //prenda.setTipo("testPrenda");
            //prenda.setTalla("testPrenda");
            prenda.setFotoEnBase64(s);
            System.out.println( "s " +i/*prenda.getFotoEnBase64()*/);
            prendaRepository.save(prenda);
            i++;
        }

        return outfit;
    }
}

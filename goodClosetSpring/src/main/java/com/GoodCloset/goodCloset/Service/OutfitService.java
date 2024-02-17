package com.GoodCloset.goodCloset.Service;

import com.GoodCloset.goodCloset.Models.Armario;
import com.GoodCloset.goodCloset.Models.ArmarioOutfit;
import com.GoodCloset.goodCloset.Models.Outfit;
import com.GoodCloset.goodCloset.Models.Prenda;
import com.GoodCloset.goodCloset.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutfitService {
    @Autowired
    private ArmarioOutfitRepository armarioOutfitRepository;
    @Autowired
    private OutfitRepository outfitRepository;
    @Autowired
    private ArmarioRepository armarioRepository;
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

        List<Outfit> listaOutfits = outfitRepository.findAll();
        Outfit outFit = listaOutfits.get(listaOutfits.size() - 1);
        Optional<Armario> armario = armarioRepository.findById(idArmario);
        ArmarioOutfit armarioOutfit;
        if (armario.isPresent()) {
            // Si el Armario existe, obtenemos la instancia y creamos y guardamos el ArmarioOutfit asociado al último Outfit y al Armario
            Armario armarioExistente = armario.get();
            System.out.println(armarioExistente);
            armarioOutfit = new ArmarioOutfit(armarioExistente, outfit);
            armarioOutfitRepository.save(armarioOutfit);

            return outfit;
        } else {
            // Manejar el caso en que el Armario no existe según tus necesidades
            throw new RuntimeException("Armario con ID " + idArmario + " no encontrado.");
        }
    }
}

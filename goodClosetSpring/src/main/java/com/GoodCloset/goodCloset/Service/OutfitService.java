package com.GoodCloset.goodCloset.Service;

import com.GoodCloset.goodCloset.Models.*;
import com.GoodCloset.goodCloset.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OutfitService {
    @Autowired
    Prenda_OutfitRepository prenda_outfitRepository;
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
        Outfit outfit = new Outfit();
        outfit.setNombre("testDefinitivo");
        outfit.setDescripcion("Outfit de prueba");
        outfit = outfitRepository.save(outfit); //insertamos el outfit

        Optional<Armario> armario = armarioRepository.findById(idArmario);
        if (armario.isPresent()) {
            Armario armarioExistente = armario.get();
            ArmarioOutfit armarioOutfit = new ArmarioOutfit(armarioExistente, outfit);
            armarioOutfitRepository.save(armarioOutfit);

            List<Prenda> listaPrendas = new ArrayList<>();
            for (String s : imagenesCapturadaBase64) {
                Prenda prenda = new Prenda();
                prenda.setFotoEnBase64(s);
                prendaRepository.save(prenda);
                listaPrendas.add(prenda); //insertamos las prendas de la lista (seran siempre 3)
            }

            // Recuperar los últimos tres IDs de las prendas y el último ID del outfit
            List<Prenda> ultimasPrendas = prendaRepository.findTop3ByOrderByIdDesc();
            for (Prenda p : ultimasPrendas){
                Prenda_Outfit prenda_outfit = new Prenda_Outfit(p, outfit);
                prenda_outfitRepository.save(prenda_outfit);  //guardamos la relacon entre prenda y outyfit
            }
            return outfit;
        } else {
            throw new RuntimeException("Armario con ID " + idArmario + " no encontrado.");
        }
    }




}

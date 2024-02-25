package com.GoodCloset.goodCloset.Service;

import com.GoodCloset.goodCloset.Models.Prenda_Outfit;
import com.GoodCloset.goodCloset.Repository.Prenda_OutfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class Prenda_OutfitService {
    @Autowired
    private Prenda_OutfitRepository prenda_outfitRepository;

    public List<Prenda_Outfit> getAllPrenda_Outfit(){
        return prenda_outfitRepository.findAll();
    }
    public Prenda_Outfit getPrenda_OutfitById(Integer id){
        return prenda_outfitRepository.findById(id).get();
    }
}

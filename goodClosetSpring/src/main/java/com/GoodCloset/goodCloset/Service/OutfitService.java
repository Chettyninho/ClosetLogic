package com.GoodCloset.goodCloset.Service;

import com.GoodCloset.goodCloset.Models.Outfit;
import com.GoodCloset.goodCloset.Repository.OutfitRepository;
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

}

package com.GoodCloset.goodCloset.Controller;

import com.GoodCloset.goodCloset.Models.Outfit;
import com.GoodCloset.goodCloset.Service.OutfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/outfit")
public class OutfitController {
@Autowired//para conectar directamente con el Repositoryç
    private OutfitService outfitService;

    @GetMapping("/all")
    public List<Outfit> getAllUsuarios(){
        return outfitService.getAllOutfits();
    }



}

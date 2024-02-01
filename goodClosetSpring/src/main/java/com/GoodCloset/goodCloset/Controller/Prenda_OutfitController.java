package com.GoodCloset.goodCloset.Controller;

import com.GoodCloset.goodCloset.Models.Armario;
import com.GoodCloset.goodCloset.Models.Prenda_Outfit;
import com.GoodCloset.goodCloset.Service.Prenda_OutfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/p-o")
public class Prenda_OutfitController {
    @Autowired
    private Prenda_OutfitService prenda_outfitService;
    @GetMapping("/all")
    public List<Prenda_Outfit> getAllPrendaOutfit(){
        return prenda_outfitService.getAllPrenda_Outfit();
    }
}

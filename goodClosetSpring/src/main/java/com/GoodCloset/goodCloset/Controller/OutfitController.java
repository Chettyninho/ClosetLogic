package com.GoodCloset.goodCloset.Controller;

import com.GoodCloset.goodCloset.Models.Outfit;
import com.GoodCloset.goodCloset.Service.OutfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/{idArmario}/newOutfit")
    public Outfit saveNewOutfit(@PathVariable Integer idArmario, @RequestBody List<String> imagenesCapturadaBase64) {
        // Implementa la lógica para guardar el nuevo outfit asociado al armario con idArmario y con las imágenes capturadas
        // Puedes utilizar outfitService.nuevoOutfit(...) y pasarle el idArmario y las imágenes capturadas
        //...
//el inconveniente es si edcide crear un nuevo armario, que tendra que poner un nombre a su armario,
        //ahi quizas deberiamos pasar un objeto armari con el nombre e insertarlo a traves de armarioService.
        //despues gestionamos las fotos con el id(que tendremos que preguntar a la bbdd cual es) y el string de imagenes
        // Devuelve el outfit guardado (esto puede variar según tu lógica)
        return outfitService.nuevoOutfit(idArmario, imagenesCapturadaBase64);
    }




}

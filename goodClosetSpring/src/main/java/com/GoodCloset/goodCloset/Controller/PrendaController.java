package com.GoodCloset.goodCloset.Controller;

import com.GoodCloset.goodCloset.Models.Prenda;
import com.GoodCloset.goodCloset.Service.PrendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/prenda")
public class PrendaController {
    @Autowired//para conectar directamente con el Repository
    private PrendaService prendaService;

    @GetMapping("/all")
    public List<Prenda> getAllPrendas() {
        return prendaService.getAllPrendas();
    }


    @GetMapping("/{idPrenda}")
    public Prenda getPrendasById(@PathVariable Integer idPrenda) {
        System.out.println(prendaService.getPrendaById(idPrenda));
        return prendaService.getPrendaById(idPrenda);
    }

    //eliminar prenda
    @DeleteMapping("/delete/{id}")
    public Boolean deletePrenda(@PathVariable Integer id){
        return prendaService.deletePrendaById(id);
    }
}


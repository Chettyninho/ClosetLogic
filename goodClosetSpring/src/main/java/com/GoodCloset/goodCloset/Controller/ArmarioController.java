package com.GoodCloset.goodCloset.Controller;

import com.GoodCloset.goodCloset.Models.Armario;
import com.GoodCloset.goodCloset.Service.ArmarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/armario")
public class ArmarioController {
@Autowired//para conectar directamente con el Repositoryç
    private ArmarioService armarioService;

    @GetMapping("/all")
    public List<Armario> getAllArmarios(){
        return armarioService.getAllArmarios();
    }

    @PostMapping("/saveArmario") //hace tanto la funcion de insert como de update.
    public Armario saveArmario(@RequestBody Armario armario){
        return armarioService.saveArmario(armario);
    }

    //obtiene todos los armarios de un mismo prpietario
    @GetMapping("/propietario/{idPropietario}")
    public List<Armario> getAllArmariosDe(@PathVariable Integer idPropietario){
        try {
            // Llama al servicio para obtener los armarios por propietario
            List<Armario> armariosDelPropietario = armarioService.findAllArmariosByPropietario(idPropietario);
            return armariosDelPropietario;
        } catch (Exception e) {
            // Maneja cualquier excepción y devuelve una lista vacía o manejo de errores según sea necesario
            return Collections.emptyList();
        }
    }



    }




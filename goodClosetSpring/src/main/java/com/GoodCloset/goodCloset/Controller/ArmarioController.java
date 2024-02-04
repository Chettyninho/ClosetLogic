package com.GoodCloset.goodCloset.Controller;

import com.GoodCloset.goodCloset.Models.Armario;
import com.GoodCloset.goodCloset.Models.Usuario;
import com.GoodCloset.goodCloset.Service.ArmarioService;
import com.GoodCloset.goodCloset.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/armario")
public class ArmarioController {
@Autowired//para conectar directamente con el Repositoryç
    private ArmarioService armarioService;

    @GetMapping("/all")
    public List<Armario> getAllArmarios(){
        return armarioService.getAllArmarios();
    }

//no termina de funcionar ->  @GetMapping("/propietario/{idPropietario}")
   // @GetMapping("/propietario/{idPropietario}")
  //  public List<Armario> getAllArmariosDe(@PathVariable Integer idPropietario){
   //     List<Armario> armariosDelPropietario = armarioService.findAllArmariosByPropietario(idPropietario);
    //    return armariosDelPropietario;
    //}




}

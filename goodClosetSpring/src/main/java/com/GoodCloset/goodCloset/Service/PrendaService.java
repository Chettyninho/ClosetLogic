package com.GoodCloset.goodCloset.Service;

import com.GoodCloset.goodCloset.Models.Prenda;
import com.GoodCloset.goodCloset.Repository.PrendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrendaService {
    @Autowired
    private PrendaRepository prendaRepository;

    public List<Prenda> getAllPrendas() {
        return prendaRepository.findAll();
    }

    public boolean deletePrendaById(Integer id) {
        // Verificar si la prenda con el ID dado existe
        if (prendaRepository.existsById(id)) {
            // Si existe, eliminar la prenda y devolver true
            prendaRepository.deleteById(id);
            return true;
        } else {
            // Si no existe, devolver false
            return false;
        }
    }
}

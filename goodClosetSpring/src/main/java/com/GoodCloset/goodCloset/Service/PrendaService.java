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
}

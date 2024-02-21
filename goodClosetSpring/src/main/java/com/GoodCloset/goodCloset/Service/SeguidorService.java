package com.GoodCloset.goodCloset.Service;

import com.GoodCloset.goodCloset.Models.Seguidor;
import com.GoodCloset.goodCloset.Repository.SeguidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SeguidorService {
    @Autowired
    private SeguidorRepository seguidorRepository;

    public List<Seguidor> obtenerSeguidores(Integer idSeguido) {
        return seguidorRepository.findBySeguidoId(idSeguido);
    }

    public List<Seguidor> obtenerSeguidos(Integer idSeguidor) {
        return seguidorRepository.findBySeguidorId(idSeguidor);
    }
}

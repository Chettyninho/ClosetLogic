package com.GoodCloset.goodCloset.Service;

import com.GoodCloset.goodCloset.Models.Seguidor;
import com.GoodCloset.goodCloset.Repository.SeguidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SeguidorService {
    //lo dicho, esta en blanco porque no se como iria, creo que no seria findAll porque seria mas adecuado algo asi como
    // buscar todos los que en id_seguido sea ? para mostrar todos los seguidores tanto nuestros como de alguien si estamos viendo un perfil
    //aunque tambien habria que hacer una consulta parecida en id_seguidor para ver a cuantos y quienes sigue
    @Autowired
    private SeguidorRepository seguidorRepository;

    public List<Seguidor> obtenerSeguidores(Integer idSeguido) {
        return seguidorRepository.findBySeguidoId(idSeguido);
    }

    public List<Seguidor> obtenerSeguidos(Integer idSeguidor) {
        return seguidorRepository.findBySeguidorId(idSeguidor);
    }
}

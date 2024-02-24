package com.GoodCloset.goodCloset.Service;

import com.GoodCloset.goodCloset.Models.Prenda_Tag;
import com.GoodCloset.goodCloset.Repository.Prenda_TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class Prenda_TagService {
    @Autowired
    private Prenda_TagRepository prenda_tagRepository;
    public List<Prenda_Tag> getAllPrenda_TagS(){
        return prenda_tagRepository.findAll();
    }
    public Prenda_Tag getPrenda_TagSById(Integer id){
        return prenda_tagRepository.findById(id).get();
    }
}



package com.GoodCloset.goodCloset.Service;

import com.GoodCloset.goodCloset.Models.Tag;
import com.GoodCloset.goodCloset.Repository.TagRepository;
import com.GoodCloset.goodCloset.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Integer id){
    return tagRepository.findById(id).get();
}
}
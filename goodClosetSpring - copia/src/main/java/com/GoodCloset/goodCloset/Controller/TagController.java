package com.GoodCloset.goodCloset.Controller;

import com.GoodCloset.goodCloset.Models.Tag;
import com.GoodCloset.goodCloset.Models.Usuario;
import com.GoodCloset.goodCloset.Service.TagService;
import com.GoodCloset.goodCloset.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
@Autowired//para conectar directamente con el Repositoryç
    private TagService tagService;

    @GetMapping("/all")
    public List<Tag> getAllTags(){
        return tagService.getAllTags();
    }


}

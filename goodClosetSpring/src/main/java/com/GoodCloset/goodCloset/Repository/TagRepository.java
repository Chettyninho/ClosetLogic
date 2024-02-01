package com.GoodCloset.goodCloset.Repository;

import com.GoodCloset.goodCloset.Models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Integer> {
    List<Tag> findAll();
}

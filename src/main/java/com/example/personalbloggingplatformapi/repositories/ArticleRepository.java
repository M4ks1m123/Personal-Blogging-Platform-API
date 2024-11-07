package com.example.personalbloggingplatformapi.repositories;


import com.example.personalbloggingplatformapi.domain.entity.ArticleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Long>
    {
    List<ArticleEntity> findAllByTag(String tag, Pageable pageable);
    List<ArticleEntity> findAll(Pageable pageable);
}

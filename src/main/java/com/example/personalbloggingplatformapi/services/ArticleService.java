package com.example.personalbloggingplatformapi.services;

import com.example.personalbloggingplatformapi.domain.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    ArticleEntity save(ArticleEntity articleEntity);

    Optional<ArticleEntity> findById(Long id);

    List<ArticleEntity> findAll();

    List<ArticleEntity> findAll(Pageable pageable);

    List<ArticleEntity> findAllByTag(String tag, Pageable pageable);

    void delete(Long id);

    ArticleEntity patchById(Long id, ArticleEntity articleEntity);

    boolean isExists(Long id);
}

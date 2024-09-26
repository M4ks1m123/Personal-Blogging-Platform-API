package com.example.personalbloggingplatformapi.services;

import com.example.personalbloggingplatformapi.domain.entity.ArticleEntity;

import java.util.Optional;

public interface ArticleService {

    public ArticleEntity save(ArticleEntity articleEntity);

    public Optional<ArticleEntity> getById(long id);
}

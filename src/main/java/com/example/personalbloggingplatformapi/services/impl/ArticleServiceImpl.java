package com.example.personalbloggingplatformapi.services.impl;

import com.example.personalbloggingplatformapi.domain.entity.ArticleEntity;
import com.example.personalbloggingplatformapi.repositories.ArticleRepository;
import com.example.personalbloggingplatformapi.services.ArticleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleEntity save(ArticleEntity articleEntity) {
        return articleRepository.save(articleEntity);
    }

    @Override
    public Optional<ArticleEntity> getById(long id) {
        return articleRepository.findById(id);
    }
}

package com.example.personalbloggingplatformapi.services.impl;

import com.example.personalbloggingplatformapi.domain.entity.ArticleEntity;
import com.example.personalbloggingplatformapi.repositories.ArticleRepository;
import com.example.personalbloggingplatformapi.services.ArticleService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleEntity save(ArticleEntity articleEntity) {
        return articleRepository.save(articleEntity);
    }

    @Override
    public Optional<ArticleEntity> findById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public List<ArticleEntity> findAll() {
        return StreamSupport
                .stream(articleRepository
                                .findAll()
                                .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleEntity> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Override
    public List<ArticleEntity> findAllByTag(String tag, Pageable pageable) {
        return articleRepository.findAllByTag(tag, pageable);
    }

    @Override
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public ArticleEntity patchById(Long id, ArticleEntity articleEntity) {
        articleEntity.setId(id);

        return articleRepository.findById(id).map(existingArticle -> {
            Optional.ofNullable(articleEntity.getTitle()).ifPresent(existingArticle::setTitle);
            Optional.ofNullable(articleEntity.getTag()).ifPresent(existingArticle::setTag);
            Optional.ofNullable(articleEntity.getPublishingDate()).ifPresent(existingArticle::setPublishingDate);
            return articleRepository.save(existingArticle);
        }).orElseThrow(()-> new RuntimeException("Article does not exist"));
    }

    @Override
    public boolean isExists(Long id) {
        return articleRepository.existsById(id);
    }
}

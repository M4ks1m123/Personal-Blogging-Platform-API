package com.example.personalbloggingplatformapi.controllers;

import com.example.personalbloggingplatformapi.domain.entities.ArticleEntity;
import com.example.personalbloggingplatformapi.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(path="/articles")
    public ResponseEntity<ArticleEntity> getArticle(@RequestBody ArticleEntity articleEntity) {

        ArticleEntity savedArticleEntity = articleService.save(articleEntity);
        return new ResponseEntity<>(savedArticleEntity, HttpStatus.CREATED);

    }
}

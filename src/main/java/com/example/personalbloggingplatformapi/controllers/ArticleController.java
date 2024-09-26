package com.example.personalbloggingplatformapi.controllers;

import com.example.personalbloggingplatformapi.domain.entity.ArticleEntity;
import com.example.personalbloggingplatformapi.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(path="/articles")
    public ResponseEntity<ArticleEntity> postArticle(@RequestBody ArticleEntity articleEntity) {

        ArticleEntity savedArticleEntity = articleService.save(articleEntity);
        return new ResponseEntity<>(savedArticleEntity, HttpStatus.CREATED);

    }

    @GetMapping(path = "/articles/{id}")
    public ResponseEntity<ArticleEntity> getArticle(@PathVariable("id") Long id){

        Optional<ArticleEntity> foundArticle = articleService.getById(id);
        return foundArticle.map(articleEntity -> new ResponseEntity<>(articleEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }
}

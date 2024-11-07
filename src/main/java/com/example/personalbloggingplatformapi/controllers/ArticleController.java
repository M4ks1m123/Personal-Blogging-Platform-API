package com.example.personalbloggingplatformapi.controllers;

import com.example.personalbloggingplatformapi.domain.entity.ArticleEntity;
import com.example.personalbloggingplatformapi.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

        Optional<ArticleEntity> foundArticle = articleService.findById(id);
        return foundArticle.map(articleEntity -> new ResponseEntity<>(articleEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping(path = "/articles")
    public List<ArticleEntity> getArticles(
            @RequestParam("tag")Optional<String> tag,
            @RequestParam(value = "page", defaultValue = "0")Integer page,
            @RequestParam(value = "size", defaultValue = "10")Integer size
            ){

        Pageable pageParams = PageRequest.of(page, size, Sort.by("publishingDate").descending());

        if(tag.isPresent()) {
            return articleService.findAllByTag(tag.get(), pageParams);
        }

        return articleService.findAll(pageParams);
    }

    @DeleteMapping(path = "/articles/{id}")
    public ResponseEntity deleteArticle(@PathVariable("id") Long id){
        articleService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = "/articles/{id}")
    public ResponseEntity<ArticleEntity> patchArticle(@PathVariable("id") Long id, @RequestBody ArticleEntity articleEntity){
        if(!articleService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ArticleEntity updatedArticleEntity = articleService.patchById(id, articleEntity);
        return new ResponseEntity<>(updatedArticleEntity, HttpStatus.OK);
    }
}

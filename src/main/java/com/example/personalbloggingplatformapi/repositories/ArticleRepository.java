package com.example.personalbloggingplatformapi.repositories;


import com.example.personalbloggingplatformapi.domain.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Long>,
    PagingAndSortingRepository<ArticleEntity, Long>{
    List<ArticleEntity> findAllByTag(String tag);
    Page<ArticleEntity> findAllByTag(String tag, Pageable pageable);
}

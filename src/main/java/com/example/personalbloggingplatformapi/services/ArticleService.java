package com.example.personalbloggingplatformapi.services;

import com.example.personalbloggingplatformapi.domain.entities.ArticleEntity;
import org.springframework.stereotype.Service;

public interface ArticleService {

    public ArticleEntity save(ArticleEntity articleEntity);
}

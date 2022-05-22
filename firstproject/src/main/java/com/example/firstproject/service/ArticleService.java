package com.example.firstproject.service;


import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //서비스 선언! 서비스 객체를 스프링부트에 생성
public class ArticleService {

    @Autowired //생성 객체를 가져와서 연결
    ArticleRepository articleRepository;

    public List<Article> index() {
       return articleRepository.findAll();
    }


    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm form) {

        Article article = form.toEntity();

        if(article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }
}

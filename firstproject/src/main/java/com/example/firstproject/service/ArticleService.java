package com.example.firstproject.service;


import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

        if(article.getId() == null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {

        Article update = dto.toEntity();
        Article target = articleRepository.findById(id).orElse(null);


        if(target != null || update.getId() != null){
            target.patch(update);
            Article updated = articleRepository.save(update);
            return updated;
        }else return null;

    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);

        if(target == null){
            return null;
        }
        articleRepository.delete(target);
        return target;
    }

    //traction
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos){

        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        articleList.stream()
                .forEach(article ->articleRepository.save(article));

        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패!")
        );

        return articleList;
    }

}

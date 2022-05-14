package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {

    @Autowired //자동으로 객체를 연결해줌
    private ArticleRepository articleRepository;

    @GetMapping("articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create") //sumit를 딱 누르면 ~ form으로 데이터가 던져짐 form action = "craete"로 던지면 PostMapping="create"를 찾아서 받는다.
    public String createArticle(ArticleForm form){

        //로깅 서버에서 일어나는 일을 기록하자
        log.info(form.toString());

        //1. DTO를 규격화 (entity)
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());

        //2. 규격화된 DTO를 DB로 저장 (repository)

        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());
        //저장시키고 반환(확인하기위해)


        return "";
    }
}

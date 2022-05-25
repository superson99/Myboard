package com.example.firstproject.service;

import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest //해당 클래스는 springboot와 연동되어 테스팅 된다.
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        //예상
        Article a = new Article(1L,"게시판 글등록!" ,"안녕하세요");
        Article b = new Article(2L,"첫글이요~!","안녕요");
        Article c = new Article(3L,"ㅋㅋ","등업점");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        //실제

        List<Article> articles = articleService.index();


        //예상과 실제가 맞나 확인
        assertEquals(expected.toString(),articles.toString());

    }
}
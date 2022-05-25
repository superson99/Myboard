package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //jpa와 연동한 테스트!
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;
    ArticleRepository articleRepository;


    @Test
    @DisplayName("특정 계시글의 모든 댓글 조회")
    void findByArticleId() {
        //입력 데이터 준비


        Long articleId = 4L;

        //Article article_4 = articleRepository.findById(articleId).orElse(null);
        Article article_4 = new Article(4L,"점심뭐먹어","댓글 ㄱ");
        Comment a = new Comment(1L,article_4, "대후니", "배불러");
        Comment b = new Comment(2L,article_4, "대후니", "근데 그냥 치킨드셈");
        Comment c = new Comment(3L,article_4, "손대훈", "햄버거");

        List<Comment> expectedComment = new ArrayList<Comment>(Arrays.asList(a,b,c));


        //실제 데이터 준비

        List<Comment> actualComment=  commentRepository.findByArticleId(articleId);

        //예상



        //결과
        assertEquals(expectedComment.toString(),actualComment.toString());

    }

    @Test
    void findByNickname() {
    }
}
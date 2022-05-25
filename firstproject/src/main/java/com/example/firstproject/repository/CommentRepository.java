package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    //특정 게시글의 모든 댓글 조회

    //커스텀으로 쿼리문 실행을 하도록 (findByArticle시에 반환하는 Comment 정보)
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId",
            nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    //특정 닉네임의 모든 댓글 조회

    //네이티브 퀄 XML
    List<Comment> findByNickname(String nickname);
}

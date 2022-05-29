package com.example.firstproject.entity;


import com.example.firstproject.dto.CommentDto;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id를 자동 생성 (id를 던져주지않아도 알아서 다음 id를 생성하여 붙여줌
    private Long id;


    //댓글과 게시글과의 관계
    @ManyToOne //여러개의 댓글 엔티티가 하나의 Article에 연관된다.
    @JoinColumn(name = "article_id") //Foregin key로 연결되는 id 열 article_id로 Article의 id과 연결됨 DB에 저장되는건 Article이 아니라 Article 의 id->article_id
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {

        //예외 발생 처리
        if(dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다");
        if(dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("게시글의 id가 잘못 되었습니다");


        return new Comment(dto.getId()
                ,article
                ,dto.getNickname()
                ,dto.getBody());
    }

    public void patch(CommentDto dto) {
        //예외발생
        if(this.id != dto.getId()) throw new IllegalArgumentException("id가 잘못 입력됨");

        if(dto.getNickname() != null) this.nickname = dto.getNickname();

        //객체 갱신
        if(dto.getBody() != null) this.body = dto.getBody();
    }
}

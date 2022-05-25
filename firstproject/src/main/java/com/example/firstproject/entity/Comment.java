package com.example.firstproject.entity;


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
    @JoinColumn(name = "article_id") //Foregin key로 연결되는 id 열 article_id로 Article의 id과 연결됨
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

}

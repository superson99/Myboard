package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor //default생성자 추가
@AllArgsConstructor //생성자 추가
@ToString
@Getter
@Entity//DB가 객체를 인식 가능하도록 객체를 DB화 하는것 (** 해당 클래스로 테이블을 만든다)
public class Article {

    @Id
    //DB가 알아서 ID값을 적용
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String title;

    @Column
    private String content;


}

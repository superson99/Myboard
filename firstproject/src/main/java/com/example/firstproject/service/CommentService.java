package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long id) {

        //조회 : 댓글들을 repository에서 가져옴
        List<Comment> comments = commentRepository.findByArticleId(id);


        //변환 : 원래는 엔티티를 가져와서 그냥 썻는데 이번에는 변환해서 사용함 Entity -> DTO로
        //원래 DTO를 Entity로 변환 하는방법을 가져오는 데이터 ArticleForm에서 바로 실행할수있게 하였음
        //하지만 이번에는 Entity를 DTO로 변환하기 때문에 수동변환이 필요함

        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for(int i = 0; i < comments.size() ; i++){
            Comment c = comments.get(i); //i번째 comment를 가져옴
            CommentDto dto = CommentDto.createCommentDto(c); // static 함수
            dtos.add(dto);
        }


        //반환 : return DTO LIST
        return dtos;
    }
}

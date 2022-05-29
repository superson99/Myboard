package com.example.firstproject.api;


import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    //댓글 목록 조회(특정 게시판의 댓글들을 모두 조회)
    @GetMapping("/api/articles/{id}/comments")
    public ResponseEntity<List<CommentDto>> show(@PathVariable Long id){
        List<CommentDto> dtos = commentService.comments(id);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    //댓글 생성

    @PostMapping("api/articles/{id}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long id, @RequestBody CommentDto dto){


        CommentDto created = commentService.commentCreate(id,dto);

        return (created.getArticleId() != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto){

        //id , nickname, body, articleid

        Comment updated = commentService.update(id,dto);

        return (dto.getArticleId() != null) ?
                ResponseEntity.status(HttpStatus.OK).body(dto):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


    }

    //댓글 삭제
}

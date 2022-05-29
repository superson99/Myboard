package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        //stream을 이용한 방법
//        commentRepository.findByArticleId(id)
//                .stream()
//                .map(Comment->CommentDto.createCommentDto(Comment))
//                .collect(Collectors.toList());
        //반환 : return DTO LIST


        return dtos;
    }

    @Transactional //DB를 건들고있기 때문에 안되면 롤백되도록 해야된다!
    public CommentDto commentCreate(Long id, CommentDto dto) {


        //게시글 조회 및 예외 발생
        Article article = articleRepository.findById(dto.getArticleId()).
                orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패"));
                //반환이 null이면 예외를 발생 시키겠다. orElseThrow()

        //댓글 엔티티 생성

        Comment commentEntity = Comment.createComment(dto, article);
        //엔티티를 DB화
        Comment created =  commentRepository.save(commentEntity);

        //DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public Comment update(Long id, CommentDto dto) {


        //변환할 댓글이 있는지 확인 dto의 id로 commentRepository의 아이디를 확인

        //댓글 조회
       Comment target = commentRepository.findById(dto.getId()).orElseThrow(()->new IllegalArgumentException("댓글이 없다."));

       //수정
       target.patch(dto);
        //변환
       Comment updated =  commentRepository.save(target);
        //리턴
        return updated;



    }
}

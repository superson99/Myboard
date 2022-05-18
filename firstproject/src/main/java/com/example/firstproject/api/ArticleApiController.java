package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){

        return articleRepository.findById(id).orElse(null);

    }
    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm form){

        //사실 Form이 아니라 json을 받는 것이다.
        Article articleEntity = form.toEntity();

      Article saved =  articleRepository.save(articleEntity);
      return saved;
    }

    //PATCH (update)
    @PatchMapping("api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){

        //1. update할 데이터를 entity 생성
        Article articleEntity = dto.toEntity();

        //2. update할 대상을 조회
        Article target = articleRepository.findById(id).orElse(null);

        //3. update할 대상이 있는지 확인 , 내가 update할 대상과 update 내용의 id 가 다른 경우 잘못된 처리
        if(target == null || articleEntity.getId() != id){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        target.patch(articleEntity);
        Article updated =  articleRepository.save(target);
            return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){

        Article articleEntity = articleRepository.findById(id).orElse(null);

        if(articleEntity == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        articleRepository.delete(articleEntity);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article,Long> {
    @Override
    List<Article> findAll();
}
//<관리대상entity,대표값(id)의 type>
//entity의 type과 호환성을 위해서 Article을 넣어준다
//entity의 type가 Article이기 때문에
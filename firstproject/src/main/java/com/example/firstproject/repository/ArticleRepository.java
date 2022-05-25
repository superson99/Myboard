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
//이 인터페이스를 통해서 CRUDRePository의 함수를 사용할 수있고
//CrudRepository는 DB에 대한 SQL문을 사용하여 DB에 데이터를 저장하거나 삭제하거나 한다.
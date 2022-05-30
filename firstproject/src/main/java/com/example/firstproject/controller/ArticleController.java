package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class ArticleController {

    @Autowired //자동으로 객체를 연결해줌
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    //sumit를 딱 누르면 ~ form으로 데이터가 던져짐 form action = "craete"로 던지면 PostMapping="create"를 찾아서 받는다.
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){

        //로깅 서버에서 일어나는 일을 기록하자
        log.info(form.toString());

        //1. DTO를 규격화 (entity)
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());

        //2. 규격화된 DTO를 DB로 저장 (repository)

        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());
        //저장시키고 반환(확인하기위해)


        return "redirect:/articles/" + saved.getId();

    }


    //웹 페이지에 DB데이터를 뿌려보자
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){

        log.info("id = " + id);
        //1. id로 데이터를 가져옴 (DB에서는 SELECT 문으로 가져와서 여기 뿌려줌)
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos= commentService.comments(id);


        //2. 가져온 데이터를 모델에 등록 ->return Html의 데이터를 담는 model에 데이터를 등록 ->html에서 article변수로 데이터를 사용할수있음
        model.addAttribute("article" , articleEntity);
                            //aritcle이라는 변수에 articleEntity를 넣는다.
        model.addAttribute("commentDtos",commentDtos);

        return"articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){

        //1. DB에 저장된 모든 article을 가져온다.
        List<Article> articleEntityList = articleRepository.findAll();

        log.info(articleEntityList.toString());

        //2. 가져온 Article묶음을 뷰로 전달
        model.addAttribute("articleList" , articleEntityList);

        //3. 뷰 페이지를 작성한다.
        return"articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id,Model model){

        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터를 등록!
        model.addAttribute("article" , articleEntity);

        //뷰 페이지 설정
        return"articles/edit";
    }

    @PostMapping("/article/Update")
    public String update(ArticleForm form){
        log.info(form.toString());

        //1. DTO를 Entity로 변경
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());;

       //2.Entity를 DB로 저장
        //2-1
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //findByid는 id로 테이블 전체를 가져온다 (article)

        if(target != null){
          Article saved = articleRepository.save(articleEntity);
        }


        //3. 변경된 데이터를 redirect

        return"redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제요청이 들어았음");

        //1.삭제 대상을 가져와보자
        Article target = articleRepository.findById(id).orElse(null);

        //2.대상을 삭제한다.
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다.");
        }


        return"redirect:/articles";
    }

//    @PostMapping("/article/comment")
//    public String commentAdd(CommentDto commentdto){
//
//        log.info(commentdto.getNickname()+ "nick name ihihi");
//        log.info(commentdto.getBody()+" hi hi hi ");
//
//        return "redirect:/articles/";
//    }
}

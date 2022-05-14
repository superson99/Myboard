package com.example.firstproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")          //greetings를 연결시키는 url
    public String niceToMeetYou(Model model){
        model.addAttribute("username","superson");
        return "greetings";        //templates안의 greetings 라는 파일을 찾아서 브라우저로 전송
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname","soni");
        return "goodbye";
    }
}

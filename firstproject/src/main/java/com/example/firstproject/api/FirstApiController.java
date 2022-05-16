package com.example.firstproject.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     //JSON을 반환하는 Controller (일반 Controller는 view template controller를 반환한다)
public class FirstApiController {

    @GetMapping("/api/hello")
        public String hello(){
            return "hello!!";
        }

}

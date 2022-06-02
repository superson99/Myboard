package com.example.firstproject.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChefTest {

    @Autowired
    IngradientFactory ingradientFactory;
    @Autowired
    Chef chef;
    @Test
            void 돈가스_요리하기() {

               // IngradientFactory ingradientFactory = new IngradientFactory();
                //준비
                String menu = "돈가스";

                //수행
                String food = chef.cook(menu);

                //예상
                String expected = "한돈 등심으로 만든 돈가스";

                //검증
                assertEquals(expected, food);
                System.out.println(food);
            }
            @Test
    void 스테이크_요리하기(){
                //AutoWird
                //IngradientFactory ingradientFactory = new IngradientFactory();
                //Chef chef = new Chef(ingradientFactory);
                //준비
                String menu = "스테이크";

                //수행
                String food = chef.cook(menu);

                //예상
                String expected = "등심으로 만든 스테이크";

                //검증
                assertEquals(expected,food);

            }
            @Test
    public void 크리스피치킨_요리하기(){
                //AutoWird
                //IngradientFactory ingradientFactory = new IngradientFactory();
                //Chef chef = new Chef(ingradientFactory);

                //준비
                String menu = "치킨";

                //수행
                String food = chef.cook(menu);

                //예상
                String expected = "국내산 10호 닭으로 만든 치킨";

                //검증
                assertEquals(expected,food);

            }

}
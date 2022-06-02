package com.example.firstproject.ioc;

import org.springframework.stereotype.Component;

@Component
public class IngradientFactory {


    public Ingradient get(String menu) {
        switch(menu){
            case "돈가스" : {
                return new Pork("한돈 등심");

            }

            case "스테이크": {
                return new Beef("등심");
            }
            case "치킨":{
                return new Chicken("국내산 10호 닭");
            }
            default: return null;
        }
    }
}

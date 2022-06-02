package com.example.firstproject.ioc;

import org.springframework.stereotype.Component;

@Component
public class Chef {

    IngradientFactory ingradientFactory;
    public String cook(String menu){

        //재료 준비
        //Pork pork  = new Pork("한우 꽃등심");
       Ingradient ingradient =  ingradientFactory.get(menu);

       //요리 반환


        return ingradient.getName() + "으로 만든 " + menu;

    }

    //외부에서 ingradientFactory에 대한 의존성 주입, 이제 Chef의 ingradientFactory는 외부에서 주입하는 객체에 의존한다.
    public Chef(IngradientFactory ingradientFactory) {
        this.ingradientFactory = ingradientFactory;
    }
}

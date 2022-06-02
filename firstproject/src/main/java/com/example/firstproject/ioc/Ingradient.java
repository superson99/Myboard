package com.example.firstproject.ioc;

public abstract class Ingradient {
    private String name;

    public Ingradient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

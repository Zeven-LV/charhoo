package com.charhoo.springlearn.bean;

public class MovieStar implements Movie{


    @Override
    public Person getPerson() {
        Person person = new Person();
        person.setName("wangwu");
        return person;
    }
}

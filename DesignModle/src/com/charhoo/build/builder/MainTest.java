package com.charhoo.build.builder;

public class MainTest {

	public static void main(String[] args) {
        BuilderCar chineseBuilder=new BuilderCarFromCN();
        BuilderCar englandBuilder=new BuilderCarFromUN();
        Install director=new Install();
        director.construct(chineseBuilder);
        Car chineseHouse=chineseBuilder.getCar();
        chineseHouse.show();
        director.construct(englandBuilder);
        Car englandHouse=englandBuilder.getCar();
        englandHouse.show();
    }
}

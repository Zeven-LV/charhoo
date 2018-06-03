package com.charhoo.build.simplefact;

public class MainTest {
	
	public static void main(String[] args) {
		Car c = CarFactory.getCar("people");
		c.trans();
	}

}

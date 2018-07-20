package com.charhoo.construct.decorator;

public class DecoratorTest {

	public static void main(String[] args) {
		CarMaker maker = new NewCar();
		CarMaker obj = new Decorator(maker);
		obj.addDoor();
		obj.addWheel();
	}
}

package com.charhoo.construct.decorator;

public class NewCar implements CarMaker{

	@Override
	public void addDoor() {
		// TODO Auto-generated method stub
		System.out.println("add door");
	}

	@Override
	public void addWheel() {
		// TODO Auto-generated method stub
		System.out.println("add wheel");
	}

}

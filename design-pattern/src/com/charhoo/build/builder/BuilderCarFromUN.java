package com.charhoo.build.builder;

public class BuilderCarFromUN extends BuilderCar{

	Car car = new Car();
	
	@Override
	public void buildDoor() {
		// TODO Auto-generated method stub
		car.add("un door");
		
	}

	@Override
	public void buildWheel() {
		// TODO Auto-generated method stub
		car.add("un wheel");
	}

	@Override
	public Car getCar() {
		// TODO Auto-generated method stub
		return car;
	}

}

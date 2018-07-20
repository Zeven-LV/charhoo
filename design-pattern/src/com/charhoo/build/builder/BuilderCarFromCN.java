package com.charhoo.build.builder;

public class BuilderCarFromCN extends BuilderCar{
	
	Car car = new Car();

	@Override
	public void buildDoor() {
		// TODO Auto-generated method stub
		car.add("cn door");
		
	}

	@Override
	public void buildWheel() {
		// TODO Auto-generated method stub
		car.add("cn wheel");
		
	}

	@Override
	public Car getCar() {
		// TODO Auto-generated method stub
		return car;
	}

}

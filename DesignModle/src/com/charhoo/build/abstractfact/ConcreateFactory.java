package com.charhoo.build.abstractfact;

public class ConcreateFactory implements AbstractFactory{

	@Override
	public BigCar bigFactory() {
		// TODO Auto-generated method stub
		return new GoodCar();
	}

	@Override
	public SmallCar smallFactory() {
		// TODO Auto-generated method stub
		return new PeopleCar();
	}

}

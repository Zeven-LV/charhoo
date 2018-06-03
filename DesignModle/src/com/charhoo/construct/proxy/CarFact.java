package com.charhoo.construct.proxy;

public class CarFact implements CarProblem{

	@Override
	public void buyCar() {
		// TODO Auto-generated method stub
		System.out.println("Factory seal you a car!");
	}

	@Override
	public void fixCar() {
		// TODO Auto-generated method stub
		System.out.println("Factory fix you car!");
	}

}

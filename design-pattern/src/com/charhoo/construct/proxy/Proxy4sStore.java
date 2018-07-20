package com.charhoo.construct.proxy;

public class Proxy4sStore implements CarProblem{
	
	private CarFact fact;
	
	public Proxy4sStore(){
		super();
		this.fact = new CarFact();
	}

	@Override
	public void buyCar() {
		// TODO Auto-generated method stub
		fact.buyCar();
		System.out.println("4sStore seal");
	}

	@Override
	public void fixCar() {
		// TODO Auto-generated method stub
		fact.fixCar();
		System.out.println("4sStore fix");
	}

}

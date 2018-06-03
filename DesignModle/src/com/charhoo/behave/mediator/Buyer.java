package com.charhoo.behave.mediator;

public class Buyer extends Custmer{

	public Buyer(Mediator mediator) {
		super(mediator);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void work() {
		// TODO Auto-generated method stub
		System.out.println("Buyer exe!");
	}

}

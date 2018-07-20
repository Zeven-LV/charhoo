package com.charhoo.construct.decorator;

public class Decorator implements CarMaker{
	
	private CarMaker maker;
	
	public Decorator(CarMaker maker){  
        super();  
        this.maker = maker;  
    }  

	@Override
	public void addDoor() {
		// TODO Auto-generated method stub

		maker.addDoor();  
	}

	@Override
	public void addWheel() {
		// TODO Auto-generated method stub
		maker.addWheel(); 
	}

}

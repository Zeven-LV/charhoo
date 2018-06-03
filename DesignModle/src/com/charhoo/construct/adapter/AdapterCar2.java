package com.charhoo.construct.adapter;

/**
 * 继承car的功能，实现BigCar的功能
 * 另一种方法，不继承Car，而是持有Car的实例
 * @author feng
 *
 */
public class AdapterCar2 implements BigCar{
	
	private Car c;
	
	public AdapterCar2(Car c){
		this.c = c;
	}

	@Override
	public void trans() {
		// TODO Auto-generated method stub
		System.out.println("BigCar trans");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		c.run();
	}
	
	

}

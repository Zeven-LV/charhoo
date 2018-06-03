package com.charhoo.construct.adapter;

/**
 * 继承car的功能，实现BigCar的功能
 * 另一种方法，不继承Car，而是持有Car的实例，见adapterCar2
 * @author feng
 *
 */
public class AdapterCar extends Car implements BigCar{

	@Override
	public void trans() {
		// TODO Auto-generated method stub
		System.out.println("BigCar trans");
	}
	
	

}

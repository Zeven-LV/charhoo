package com.charhoo.construct.adapter;


/**
 * 此例不准确
 * @author feng
 *
 */
public class AdapterTest {

	public static void main(String[] args) {
		adapter1();
		System.out.println("---------------------");
		adapter2();
		System.out.println("---------------------");
		interfaceAdapter();
	}
	
	public static void adapter1(){
		BigCar target = new AdapterCar();
		target.run();
		target.trans();
	}
	
	public static void adapter2(){
		Car c = new Car();
		BigCar target = new AdapterCar2(c);
		target.run();
		target.trans();
	}
	
	public static void interfaceAdapter(){
		InterfaceBigCar peopleCar = new InterfaceBigPeopleCar();
		peopleCar.run();
		InterfaceBigCar goodsCar = new InterfaceBigGoodsCar();
		goodsCar.trans();
	}
	
}

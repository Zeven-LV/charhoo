package com.charhoo.build.single;

public class SingleHungry2 {

	private static SingleHungry2 instance = null;
	
	static{
		instance = new SingleHungry2();
	}

	private SingleHungry2() {
	}

	public static SingleHungry2 getInstance() {
		return instance;
	}

}

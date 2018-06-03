package com.charhoo.build.single;

public class SingleHungry {

	private static SingleHungry instance = new SingleHungry();

	private SingleHungry() {
	}

	public static SingleHungry getInstance() {
		return instance;
	}

}

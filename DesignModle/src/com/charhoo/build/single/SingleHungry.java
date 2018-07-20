package com.charhoo.build.single;

/**
 * 饿汉型单例类
 *
 */
public class SingleHungry {

	private static SingleHungry instance = new SingleHungry();

	private SingleHungry() {
	}

	public static SingleHungry getInstance() {
		return instance;
	}

}

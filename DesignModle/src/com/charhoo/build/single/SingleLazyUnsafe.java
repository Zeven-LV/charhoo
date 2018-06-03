package com.charhoo.build.single;

public class SingleLazyUnsafe {

	private static SingleLazyUnsafe instance;

	private SingleLazyUnsafe (){}

	public static SingleLazyUnsafe getInstance() {
		if (instance == null) {
			instance = new SingleLazyUnsafe();
		}
		return instance;
	}
}

package com.charhoo.build.single;

/**
 * 不是线程安全的懒汉型单例模式
 *
 */
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

package com.charhoo.build.single;

/**
 * 懒汉型的单例模式
 * 使用同步来保证创建实例的线程安全
 */
public class SingleLazySafe {

	private static SingleLazySafe instance;

	private SingleLazySafe (){}

	public static synchronized SingleLazySafe getInstance() {
		if (instance == null) {
			instance = new SingleLazySafe();
		}
		return instance;
	}

}

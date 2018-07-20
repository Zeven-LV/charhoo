package com.charhoo.build.single;

/**
 *
 * 静态内部类
 */
public class SingleStaticInClass {

	private static class SingletonHolder {
		private static final SingleStaticInClass INSTANCE = new SingleStaticInClass();
	}

	private SingleStaticInClass() {
	}

	public static final SingleStaticInClass getInstance() {
		return SingletonHolder.INSTANCE;
	}

}

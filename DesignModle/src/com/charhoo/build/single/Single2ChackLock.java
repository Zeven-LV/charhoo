package com.charhoo.build.single;

public class Single2ChackLock {

	//用volatile修饰的变量，线程在每次使用变量的时候，都会读取变量修改后的最的值。volatile很容易被误用，用来进行原子性操作。
	private volatile static Single2ChackLock singleton;

	private Single2ChackLock() {
	}

	public static Single2ChackLock getSingleton() {
		if (singleton == null) {
			synchronized (Single2ChackLock.class) {
				if (singleton == null) {
					singleton = new Single2ChackLock();
				}
			}
		}
		return singleton;
	}

}

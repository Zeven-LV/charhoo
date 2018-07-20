package com.charhoo.build.single;

/**
 * 双重检验锁
 */
public class Single2ChackLock {

	//��volatile���εı������߳���ÿ��ʹ�ñ�����ʱ�򣬶����ȡ�����޸ĺ�����ֵ��volatile�����ױ����ã���������ԭ���Բ�����
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

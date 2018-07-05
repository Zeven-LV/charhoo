package com.charhoo.javaBase.jvm.memory;

/**
 * -Xmx��-Xms
 * Xmn ������
 * Xmn��newRatio ��ʱ�����������
 * Xmn��ServiceRatio eden��serviver����
 * @author feng
 *
 */

public class JVMMemory {
	
	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().maxMemory());
		System.out.println(Runtime.getRuntime().totalMemory());
		System.out.println(Runtime.getRuntime().freeMemory());
	}

}

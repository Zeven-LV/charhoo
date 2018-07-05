package com.javaBase.jvm.memory;

/**
 * -Xmx与-Xms
 * Xmn 新生代
 * Xmn：newRatio 新时代老年代比率
 * Xmn：ServiceRatio eden与serviver比例
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

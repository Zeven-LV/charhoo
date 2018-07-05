package com.javaBase.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * ERROR: Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * @author feng
 *
 */
public class OOMHeap {
	
	public static void main(String[] args) {
		List<Byte[]> list = new ArrayList<>();
		for(int i = 0;i<1024;i++){
			System.out.println(i);
			Byte[] byt = new Byte[1024*1024];
			list.add(byt);
		}
	}

}

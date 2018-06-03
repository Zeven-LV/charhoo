package com.javaBase.collection;

/**
 * ≈≈–Ú∑Ω Ω
 * @author feng
 *
 */
public class ArrayTest {
	
	public static void main(String[] args) {
		String[] a;
		String[] b = new String[5];
		for(int i = 0; i < b.length; i++)
		      b[i] = "w";
		a=b;
		System.out.println(a.length);
	}

}

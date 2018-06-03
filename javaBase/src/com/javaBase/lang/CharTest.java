package com.javaBase.lang;


/**
 * charÕ¼2×Ö½Ú£¬16Î»£¬·¶Î§0-2^16
 * 
 *
 */
public class CharTest {
	
	public static void main(String[] args) {
		toInt();
//		toUpperCase();
	}
	
	public static void toInt() {
		char a = 'A';
		System.out.println((int)a);
	}
	
	public static void toUpperCase() {
		char a = 'a';
		System.out.println(Character.toUpperCase(a));
	}

}

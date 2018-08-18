package com.charhoo.javaBase.lang;


import java.util.Arrays;
import java.util.List;

/**
 * charռ2�ֽڣ�16λ����Χ0-2^16
 * 
 *
 */
public class CharTest {
	
	public static void main(String[] args) {
//		toInt();
//		toUpperCase();
		String str = "a1A2";
		byte[] nums = new byte[]{48,49,50,51,52,53,54,55,56,57,58};
		List bytesl = Arrays.asList(nums);
		byte[] bytes = str.getBytes();

		for(byte b : bytes){
			if(bytesl.contains(b)){
			}
		}
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

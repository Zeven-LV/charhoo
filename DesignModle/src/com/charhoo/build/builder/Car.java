package com.charhoo.build.builder;

import java.util.ArrayList;

public class Car {
	private ArrayList<String> parts = new ArrayList<String>();

	public void add(String str) {
		parts.add(str);
	}

	public void show() {
		for (String part : parts) {
			System.out.println(part + "\t");
		}
		System.out.println("\n");
	}
}

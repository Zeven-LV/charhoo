package com.charhoo.build.abstractfact;

public class MainTest {
	
	public static void main(String[] args) {
		
		AbstractFactory af = new ConcreateFactory();
		af.bigFactory().trans();
		af.smallFactory().trans();
	}

}

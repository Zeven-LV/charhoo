package com.charhoo.construct.proxy;

public class ProxyTest {
	
	public static void main(String[] args) {
		
		CarProblem problem = new Proxy4sStore();
		problem.buyCar();
		problem.fixCar();
	}

}

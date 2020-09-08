package com.charhoo.behave.strategy;

public class Test {

	public static void main(String[] args) {
		Context context = new Context(new ConcreteStrategyA());
		context.contextInterface();
	}

}

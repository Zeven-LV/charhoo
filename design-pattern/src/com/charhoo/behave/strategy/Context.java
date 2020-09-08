package com.charhoo.behave.strategy;

public class Context {

	private Strategy strategy;

	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	//上下文接口
	public void contextInterface() {
		strategy.algorithmInterface();
	}

}

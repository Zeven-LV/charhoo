package com.charhoo.behave.command;

public class Test {

	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		Command cmd = new MyCommand(receiver, "clear enamy");
		Invoker invoker = new Invoker(cmd);
		invoker.action();
	}

}

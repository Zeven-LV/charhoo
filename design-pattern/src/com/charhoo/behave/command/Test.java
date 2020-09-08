package com.charhoo.behave.command;

/**
 * 调用者（invoke）-》命令（command）-》接收者（receiver）
 */
public class Test {

	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		Command cmd = new MyCommand(receiver, "clear enamy");
		Invoker invoker = new Invoker(cmd);
		invoker.action();
	}

}

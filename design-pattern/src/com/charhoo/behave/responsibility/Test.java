package com.charhoo.behave.responsibility;

public class Test {

	public static void main(String[] args) {
		MyHandler h1 = new MyHandler("h1");
		MyHandler h2 = new MyHandler("h2");
		MyHandler h3 = new MyHandler("h3");

		h1.setNext(h2);
		h2.setNext(h3);

		h1.operator();
	}

}

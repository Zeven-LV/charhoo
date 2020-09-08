package com.charhoo.behave.responsibility;

public abstract class AbstractHandler {

	private Handler next;

	public Handler getNext() {
		return next;
	}

	public void setNext(Handler next) {
		this.next = next;
	}

}

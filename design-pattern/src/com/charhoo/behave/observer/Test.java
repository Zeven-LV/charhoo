package com.charhoo.behave.observer;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Subject sub = new MySubject();//我的订阅
		sub.add(new SubObserver1());//订阅1-观察者
		sub.add(new SubObserver2());//订阅2-观察者

		sub.operation();//操作以后才会显现出变更
	}

}

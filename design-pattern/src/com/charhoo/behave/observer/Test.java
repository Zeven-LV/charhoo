package com.charhoo.behave.observer;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Subject sub = new MySubject();//�ҵĶ���
		sub.add(new SubObserver1());//����1-�۲���
		sub.add(new SubObserver2());//����2-�۲���

		sub.operation();//�����Ժ�Ż����ֳ����
	}

}

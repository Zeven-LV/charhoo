package com.charhoo.construct.adapter;

/**
 * �̳�car�Ĺ��ܣ�ʵ��BigCar�Ĺ���
 * ��һ�ַ��������̳�Car�����ǳ���Car��ʵ��
 * @author feng
 *
 */
public class AdapterCar2 implements BigCar{
	
	private Car c;
	
	public AdapterCar2(Car c){
		this.c = c;
	}

	@Override
	public void trans() {
		// TODO Auto-generated method stub
		System.out.println("BigCar trans");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		c.run();
	}
	
	

}

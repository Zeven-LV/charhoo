package com.charhoo.construct.adapter;

/**
 * �̳�car�Ĺ��ܣ�ʵ��BigCar�Ĺ���
 * ��һ�ַ��������̳�Car�����ǳ���Car��ʵ������adapterCar2
 * @author feng
 *
 */
public class AdapterCar extends Car implements BigCar{

	@Override
	public void trans() {
		// TODO Auto-generated method stub
		System.out.println("BigCar trans");
	}
	
	

}

package com.charhoo.build.prototype;


/**
 * ע�������ǳ����������
 * ǳ����: ��ֵ���͵ĳ�Ա��������ֵ�ĸ���,���������͵ĳ�Ա����ֻ��������,���������õĶ���.
 * ���: ��ֵ���͵ĳ�Ա��������ֵ�ĸ���,���������͵ĳ�Ա����Ҳ�������ö���ĸ���.
 * @author feng
 *
 */
public class MainTest {

	public static void main(String[] args) {
		testPrototypeShallow1();
		System.out.println("--------------------------");
		testPrototypeShallow2();
		System.out.println("--------------------------");
		testPrototypeDeep();
		System.out.println("--------------------------");
		testPrototypeSerialize();
	}

	private static void testPrototypeShallow1() {
		PrototypeShallowCopy1 pro = new PrototypeShallowCopy1();
		pro.setName("original object");
		PrototypeShallowCopy1 pro1 = (PrototypeShallowCopy1) pro.clone();
		pro.setName("changed object1");

		System.out.println("original object:" + pro.getName());
		System.out.println("cloned object:" + pro1.getName());

	}
	
	private static void testPrototypeShallow2() {
		PrototypeShallow pro = new PrototypeShallow();
		  pro.setName("original object");
		  PrototypeShallowCopy2 newObj = new PrototypeShallowCopy2();
		  newObj.setId("test1");
		  newObj.setPrototype(pro);
		  
		  PrototypeShallowCopy2 copyObj = (PrototypeShallowCopy2)newObj.clone();
		  copyObj.setId("testCopy");
		  copyObj.getPrototype().setName("changed object");
		  
		  System.out.println("original object id:" + newObj.getId());
		  System.out.println("original object name:" + newObj.getPrototype().getName());
		  
		  System.out.println("cloned object id:" + copyObj.getId());
		  System.out.println("cloned object name:" + copyObj.getPrototype().getName());
		  

	}
	
	 private static void testPrototypeDeep(){
		  PrototypeDeep pro = new PrototypeDeep();
		  pro.setName("original object");
		  PrototypeDeepCopy newObj = new PrototypeDeepCopy();
		  newObj.setId("test1");
		  newObj.setPrototype(pro);
		  
		  PrototypeDeepCopy copyObj = (PrototypeDeepCopy)newObj.clone();
		  copyObj.setId("testCopy");
		  copyObj.getPrototype().setName("changed object");
		  
		  System.out.println("original object id:" + newObj.getId());
		  System.out.println("original object name:" + newObj.getPrototype().getName());
		  
		  System.out.println("cloned object id:" + copyObj.getId());
		  System.out.println("cloned object name:" + copyObj.getPrototype().getName());
		  
		 }
	 
	 private static void testPrototypeSerialize(){
		 PrototypeSerialize po = new PrototypeSerialize();
		  po.setName("test1");
		  PrototypeSerializeFather se = new PrototypeSerializeFather();
		  se.setPrototype(po);
		  
		  PrototypeSerializeFather deepClone = (PrototypeSerializeFather)se.clone();
		  deepClone.getPrototype().setName("test2");
		  
		  System.out.println("original name:" + se.getPrototype().getName());
		  System.out.println("cloned name:" + deepClone.getPrototype().getName());
		  
		 }

}

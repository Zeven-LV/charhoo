package com.charhoo.build.single;

public class TestMain {

	public static void main(String[] args){  
		SingleLazySafe ts1 = SingleLazySafe.getInstance();  
        SingleLazySafe ts2 = SingleLazySafe.getInstance();  
        int s = SingleEnum.INSTANCE.instance();
        System.out.println(s);
          
        if(ts1 == ts2){  
            System.out.println("��������ͬһ��ʵ��");  
        }else{  
            System.out.println("�����Ĳ���ͬһ��ʵ��");  
        }  
    }  
}

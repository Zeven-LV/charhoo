package com.charhoo.build.single;

public class TestMain {

	public static void main(String[] args){  
		SingleLazySafe ts1 = SingleLazySafe.getInstance();  
        SingleLazySafe ts2 = SingleLazySafe.getInstance();  
        int s = SingleEnum.INSTANCE.instance();
        System.out.println(s);
          
        if(ts1 == ts2){  
            System.out.println("创建的是同一个实例");  
        }else{  
            System.out.println("创建的不是同一个实例");  
        }  
    }  
}

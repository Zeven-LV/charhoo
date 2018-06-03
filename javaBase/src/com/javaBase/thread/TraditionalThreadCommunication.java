package com.javaBase.thread;


/*
 * 子线程循环10次，接着主线程循环100次，循环50次
*/

public class TraditionalThreadCommunication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 1;i<=50;i++){
					for(int j = 1;j<=10;j++){
						System.out.println("sub thread :" + i + "-" + j);;
					}
				}
			}
		}).start();
		
//		new Thread().start();
	}

}

package com.javaBase.io;

import java.nio.ByteBuffer;



/**
 * 缓存区是数组，存储不同数据类型
 * 不同的数据类型有不同的缓存区（除了Boolean）
 * 
 * 通过allocate()获取缓冲区
 * 存取数据的核心方法  put（），get（）
 * buffer 4个核心属性：capacity（容量），limit（界限，可操作数据 的容量），position（缓冲区正在操作数据的位置）
 * mark(标记，记录position位置，可通过reset() 回到mark 位置)
 * 0〈==mark〈=position《=limit<=capacity
 * @author feng
 *
 */
public class TestBuffer {
	
//	public static void main(String[] args) {
//		String str = "abcde";
//		ByteBuffer buf = ByteBuffer.allocate(1024);//0，1024，1024
//		buf.put(str.getBytes());//5，1024，1024
//		buf.flip();//切换到读取数据模式   0，5，1024
//		byte[] dst = new byte[buf.limit()];
//		buf.get(dst);//5,5,1024
//		buf.rewind();//重复读数据  0，5，1024
//		buf.clear();//情况缓冲区（数据依然存在）  0，1024，1024
//		System.out.println(buf.position());
//		System.out.println(buf.limit());
//		System.out.println(buf.capacity());
//	}
	
	public static void main(String[] args) {
		String str = "abcde";
		ByteBuffer buf = ByteBuffer.allocate(1024);//0，1024，1024
		buf.put(str.getBytes());
		buf.flip();
		byte[] dst = new byte[buf.limit()];
		buf.get(dst,0,2);
		System.out.println(new String(dst,0,2));
		System.out.println(buf.position());
		buf.mark();//标记
		buf.get(dst,2,2);
		System.out.println(new String(dst,0,2));
		System.out.println(buf.position());
		buf.reset();//恢复到mark
		System.out.println(buf.position());
		
		//缓冲区是否有剩余数据
		if(buf.hasRemaining()){
			System.out.println(buf.remaining());
		}
	}

}

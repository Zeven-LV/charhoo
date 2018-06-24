package com.javaBase.io;

import java.nio.ByteBuffer;



/**
 * �����������飬�洢��ͬ��������
 * ��ͬ�����������в�ͬ�Ļ�����������Boolean��
 * 
 * ͨ��allocate()��ȡ������
 * ��ȡ���ݵĺ��ķ���  put������get����
 * buffer 4���������ԣ�capacity����������limit�����ޣ��ɲ������� ����������position�����������ڲ������ݵ�λ�ã�
 * mark(��ǣ���¼positionλ�ã���ͨ��reset() �ص�mark λ��)
 * 0��==mark��=position��=limit<=capacity
 * @author feng
 *
 */
public class TestBuffer {
	
//	public static void main(String[] args) {
//		String str = "abcde";
//		ByteBuffer buf = ByteBuffer.allocate(1024);//0��1024��1024
//		buf.put(str.getBytes());//5��1024��1024
//		buf.flip();//�л�����ȡ����ģʽ   0��5��1024
//		byte[] dst = new byte[buf.limit()];
//		buf.get(dst);//5,5,1024
//		buf.rewind();//�ظ�������  0��5��1024
//		buf.clear();//�����������������Ȼ���ڣ�  0��1024��1024
//		System.out.println(buf.position());
//		System.out.println(buf.limit());
//		System.out.println(buf.capacity());
//	}
	
	public static void main(String[] args) {
		String str = "abcde";
		ByteBuffer buf = ByteBuffer.allocate(1024);//0��1024��1024
		buf.put(str.getBytes());
		buf.flip();
		byte[] dst = new byte[buf.limit()];
		buf.get(dst,0,2);
		System.out.println(new String(dst,0,2));
		System.out.println(buf.position());
		buf.mark();//���
		buf.get(dst,2,2);
		System.out.println(new String(dst,0,2));
		System.out.println(buf.position());
		buf.reset();//�ָ���mark
		System.out.println(buf.position());
		
		//�������Ƿ���ʣ������
		if(buf.hasRemaining()){
			System.out.println(buf.remaining());
		}
	}

}

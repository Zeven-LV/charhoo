package com.javaBase.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 
 * 
 * @author feng
 *
 */
public class TestNio4 {
	
	public static void main(String[] args) {
		//��ȡ�ܵ�
		Pipe pipe;
		try {
			pipe = Pipe.open();

			Pipe.SinkChannel  sinkChannle = pipe.sink();
			
			ByteBuffer buf = ByteBuffer.allocate(1024);
			
			//����д��ܵ�
			buf.put("ͨ���ܵ���������".getBytes());
			buf.flip();
			sinkChannle.write(buf);
			
			//���ܵ����ݵ�����
			Pipe.SourceChannel sourceChannle = pipe.source();
			
//			System.out.println(new String(buf.array(),0,buf.limit()));
			buf.flip();
			int len = sourceChannle.read(buf);
			System.out.println(new String(buf.array(),0,len));
			
			sinkChannle.close();
			sourceChannle.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}


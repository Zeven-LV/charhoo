package com.charhoo.javaBase.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * DatagramSocket
 * 
 * @author feng
 *
 */
public class TestNio3 {
	
	public static void main(String[] args) {
		new TestNio3.Receive().start();
		new TestNio3.Send().start();
	}
	
	public static class Send extends Thread {
		
		public Send(){}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				
				DatagramChannel dChannle = DatagramChannel.open();
				
				//�л�������ģʽ
				dChannle.configureBlocking(false);
				
				
				ByteBuffer buf = ByteBuffer.allocate(1024);
				
				//
				Scanner scan = new Scanner(System.in);
				while(scan.hasNext()){
					String str = scan.next();
					buf.put((new Date().toString() +"\n" + str).getBytes());
					buf.flip();
					dChannle.send(buf, new InetSocketAddress("127.0.0.1", 8999));
					buf.clear();
				}
				
				
				dChannle.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static class Receive extends Thread {
		
		public Receive(){}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				
				DatagramChannel dChannle = DatagramChannel.open();
				
				//�л�������ģʽ
				dChannle.configureBlocking(false);
				
				dChannle.bind(new InetSocketAddress(8999));
				
				//��ȡѡ����
				Selector selector = Selector.open();
				
				//��ͨ��ע�ᵽѡ����,����������״̬
				dChannle.register(selector, SelectionKey.OP_READ);
				
				//��ѯ��ȡѡ������׼���������¼�
				while(selector.select() > 0){
					//��ȡ����ѡ���¼�״̬
					Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
					while(keys.hasNext()){
						SelectionKey key = keys.next();
						//�����ܾ�������ȡ�ͻ�������
						if(key.isReadable()){
							ByteBuffer buf = ByteBuffer.allocate(1024);
							dChannle.receive(buf);
							buf.flip();
							System.out.println(new String(buf.array(),0,buf.limit()));
							buf.clear();
						}
						
					}
					//ȡ��ѡ���
					keys.remove();
				}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}


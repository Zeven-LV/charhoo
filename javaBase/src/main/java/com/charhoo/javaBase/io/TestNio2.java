package com.charhoo.javaBase.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * ������ ʽ
 * nio���������ģ�1��ͨ������selectChannle��2�����壬3��ѡ����
 * selectChannle��1.socketChannle 2��ServerSocketChannle 3��datagramChannle  4,Pipe.sinkChannle, 5,Pipe.socketChannle
 * 
 * nioֻ����������io
 * 
 * @author feng
 *
 */
public class TestNio2 {
	
	public static void main(String[] args) {
		new TestNio2.Server().start();
		new TestNio2.Client().start();
	}
	
	public static class Client extends Thread {
		
		public Client(){}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				SocketChannel sChannle = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8999));
				
				//�л�������ģʽ
				sChannle.configureBlocking(false);
				
				
				ByteBuffer buf = ByteBuffer.allocate(1024);
				
				//
				Scanner scan = new Scanner(System.in);
				while(scan.hasNext()){
					String str = scan.next();
					buf.put((new Date().toString() +"\n" + str).getBytes());
					buf.flip();
					sChannle.write(buf);
					buf.clear();
				}
				
				
				sChannle.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static class Server extends Thread {
		
		public Server(){}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				ServerSocketChannel ssChannle = ServerSocketChannel.open();
				ssChannle.configureBlocking(false);
				ssChannle.bind(new InetSocketAddress(8999));
				
				//��ȡѡ����
				Selector selector = Selector.open();
				
				//��ͨ��ע�ᵽѡ����,����������״̬
				ssChannle.register(selector, SelectionKey.OP_ACCEPT);
				
				//��ѯ��ȡѡ������׼���������¼�
				while(selector.select() > 0){
					//��ȡ����ѡ���¼�״̬
					Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
					while(keys.hasNext()){
						SelectionKey key = keys.next();
						//�����ܾ�������ȡ�ͻ�������
						if(key.isAcceptable()){
							SocketChannel sChannle = ssChannle.accept();
							sChannle.configureBlocking(false);
							sChannle.register(selector, SelectionKey.OP_READ);
						}else if(key.isReadable()){
							SocketChannel sChannle = (SocketChannel) key.channel();
							ByteBuffer buf = ByteBuffer.allocate(1024);
							int len = 0;
							while((len = sChannle.read(buf)) != -1){
								buf.flip();
								System.out.println(new String(buf.array(),0,len));
								buf.clear();
							}
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
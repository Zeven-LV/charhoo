package com.javaBase.io;

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
				
				//切换非阻塞模式
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
				
				//切换非阻塞模式
				dChannle.configureBlocking(false);
				
				dChannle.bind(new InetSocketAddress(8999));
				
				//获取选择器
				Selector selector = Selector.open();
				
				//把通道注册到选择器,并监听接受状态
				dChannle.register(selector, SelectionKey.OP_READ);
				
				//轮询获取选择器上准备就绪的事件
				while(selector.select() > 0){
					//获取所有选择事件状态
					Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
					while(keys.hasNext()){
						SelectionKey key = keys.next();
						//若接受就绪，获取客户端连接
						if(key.isReadable()){
							ByteBuffer buf = ByteBuffer.allocate(1024);
							dChannle.receive(buf);
							buf.flip();
							System.out.println(new String(buf.array(),0,buf.limit()));
							buf.clear();
						}
						
					}
					//取消选择键
					keys.remove();
				}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}


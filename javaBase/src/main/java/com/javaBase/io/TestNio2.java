package com.javaBase.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 非阻塞 式
 * nio；三个核心：1，通道，（selectChannle）2，缓冲，3，选择器
 * selectChannle：1.socketChannle 2，ServerSocketChannle 3，datagramChannle  4,Pipe.sinkChannle, 5,Pipe.socketChannle
 * 
 * nio只适用与网络io
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
				
				//切换非阻塞模式
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
				
				//获取选择器
				Selector selector = Selector.open();
				
				//把通道注册到选择器,并监听接受状态
				ssChannle.register(selector, SelectionKey.OP_ACCEPT);
				
				//轮询获取选择器上准备就绪的事件
				while(selector.select() > 0){
					//获取所有选择事件状态
					Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
					while(keys.hasNext()){
						SelectionKey key = keys.next();
						//若接受就绪，获取客户端连接
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
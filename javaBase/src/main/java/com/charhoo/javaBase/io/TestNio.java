package com.charhoo.javaBase.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 
 * ����ʽ
 * 
 * @author feng
 *
 */
public class TestNio {
	
	public static void main(String[] args) {
		new TestNio.Server().start();
		new TestNio.Client().start();
	}
	
	public static class Client extends Thread {
		
		public Client(){}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				SocketChannel sChannle = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8999));
				
				FileChannel fChannle = FileChannel.open(Paths.get("1.txt"), StandardOpenOption.READ);
				
				ByteBuffer buf = ByteBuffer.allocate(1024);
				
				while(fChannle.read(buf) != -1){
					buf.flip();
					sChannle.write(buf);
					buf.clear();
				}
				
				//���ܷ�������Ϣ
				sChannle.shutdownOutput();
				int len = 0;
				while((len = sChannle.read(buf)) != -1){
					buf.flip();
					System.out.println(new String(buf.array(),0,len));
					buf.clear();
				}
				
				sChannle.close();
				fChannle.close();
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
				ssChannle.bind(new InetSocketAddress(8999));
				
				SocketChannel sChannle = ssChannle.accept();
				
				FileChannel fChannle = FileChannel.open(Paths.get("3.txt"), StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW);
				
				ByteBuffer buf = ByteBuffer.allocate(1024);
				
				while(sChannle.read(buf) != -1){
					buf.flip();
					fChannle.write(buf);
					buf.clear();
				}
				
				//���͸��ͻ���
				buf.put("�������������ݳɹ�".getBytes());
				buf.flip();
				sChannle.write(buf);
				
				sChannle.close();
				ssChannle.close();
				fChannle.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

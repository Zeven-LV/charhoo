package com.javaBase.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;



/**
 * 直接缓冲区，allocateDirect() 直接创建到物理内存,效率高不安全，不受控制
 * 非直接缓冲区，allocate() 创建到JVM 
 * 
 * 通道（channel），源节点，目标节点的链接
 * 主要实现：FileChannel，SocketChannel（tcp），ServerSocketChannel（tcp），DatagramChannel
 * 获取通道：
 * 1，getChannel
 * 		本地io：FileInputStearm/output
 * 		  网络io：socket，serverSocket，dataGram
 * 2，NIO2 open()
 * 3, NIO2 Files 工具类 getByteChannel
 * 
 * 
 * 四   通道之间的数据传输
 * transferFrom（）
 * transferTo（）
 * 
 * 五 分散（Scatter）于聚集（Gather）
 * 分散：将通道中的数据分散到多个缓冲区（顺序）；
 * 聚基：将多个缓冲去的数据聚集到通道中
 * @author feng
 *
 */
public class TestBuffer2 {
	
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocateDirect(1024);
		System.out.println(buf.isDirect());
	}
	
	//通道实现文件复制(非直接)
	public static void main1(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel inChannle = null;
		FileChannel outChannle = null;
		try {
			fis = new FileInputStream("1.jpg");
			fos = new FileOutputStream("2.jpg");
			
			inChannle = fis.getChannel();
			outChannle = fos.getChannel();
			
			ByteBuffer buf = ByteBuffer.allocate(1024);
			
			while(inChannle.read(buf) != -1){
				buf.flip();
				outChannle.write(buf);
				buf.clear();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(fis!= null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	
	//通道实现文件复制(直接,物理内存)
	public static void main3(String[] args) {
		
		try {
			FileChannel inChannle = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
			FileChannel outChannle = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
			//内存映射文件
			MappedByteBuffer inMappedBuf = inChannle.map(MapMode.READ_ONLY, 0, inChannle.size());
			MappedByteBuffer outMappedBuf = outChannle.map(MapMode.READ_WRITE, 0, inChannle.size());
			
			//直接对缓冲区数据进行操作
			byte[] dst = new byte[inMappedBuf.limit()];
			inMappedBuf.get(dst);
			outMappedBuf.put(dst);
			
			inChannle.close();
			outChannle.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//通道直接传输
	public static void main4(String[] args) {
		
		try {
			FileChannel inChannle = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
			FileChannel outChannle = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
			
//			inChannle.transferTo(0, inChannle.size(), outChannle);
			outChannle.transferFrom(inChannle, 0, inChannle.size());
			
			inChannle.close();
			outChannle.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//分散聚集
	public static void main5(String[] args) {
		
		try {
			RandomAccessFile raf1 = new RandomAccessFile("1.txt","rw");
			
			FileChannel channle1 = raf1.getChannel();
			//分配指定大小缓冲区
			ByteBuffer buf1 = ByteBuffer.allocate(100);
			ByteBuffer buf2 = ByteBuffer.allocate(1024);
			
			//分散读取
			ByteBuffer[] bufs = {buf1,buf2};
			channle1.read(bufs);
			
			for(ByteBuffer buf : bufs){
				buf.flip();
			}
			
			System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
			System.out.println(new String(bufs[1].array(),0,bufs[1].limit()));
			
			
			//聚集写入
			RandomAccessFile raf2 = new RandomAccessFile("2.txt","rw");
			FileChannel channle2 = raf2.getChannel();
			channle2.write(bufs);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

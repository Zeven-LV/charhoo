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
 * ֱ�ӻ�������allocateDirect() ֱ�Ӵ����������ڴ�,Ч�ʸ߲���ȫ�����ܿ���
 * ��ֱ�ӻ�������allocate() ������JVM 
 * 
 * ͨ����channel����Դ�ڵ㣬Ŀ��ڵ������
 * ��Ҫʵ�֣�FileChannel��SocketChannel��tcp����ServerSocketChannel��tcp����DatagramChannel
 * ��ȡͨ����
 * 1��getChannel
 * 		����io��FileInputStearm/output
 * 		  ����io��socket��serverSocket��dataGram
 * 2��NIO2 open()
 * 3, NIO2 Files ������ getByteChannel
 * 
 * 
 * ��   ͨ��֮������ݴ���
 * transferFrom����
 * transferTo����
 * 
 * �� ��ɢ��Scatter���ھۼ���Gather��
 * ��ɢ����ͨ���е����ݷ�ɢ�������������˳�򣩣�
 * �ۻ������������ȥ�����ݾۼ���ͨ����
 * @author feng
 *
 */
public class TestBuffer2 {
	
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocateDirect(1024);
		System.out.println(buf.isDirect());
	}
	
	//ͨ��ʵ���ļ�����(��ֱ��)
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
	
	
	//ͨ��ʵ���ļ�����(ֱ��,�����ڴ�)
	public static void main3(String[] args) {
		
		try {
			FileChannel inChannle = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
			FileChannel outChannle = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
			//�ڴ�ӳ���ļ�
			MappedByteBuffer inMappedBuf = inChannle.map(MapMode.READ_ONLY, 0, inChannle.size());
			MappedByteBuffer outMappedBuf = outChannle.map(MapMode.READ_WRITE, 0, inChannle.size());
			
			//ֱ�ӶԻ��������ݽ��в���
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
	
	//ͨ��ֱ�Ӵ���
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
	
	//��ɢ�ۼ�
	public static void main5(String[] args) {
		
		try {
			RandomAccessFile raf1 = new RandomAccessFile("1.txt","rw");
			
			FileChannel channle1 = raf1.getChannel();
			//����ָ����С������
			ByteBuffer buf1 = ByteBuffer.allocate(100);
			ByteBuffer buf2 = ByteBuffer.allocate(1024);
			
			//��ɢ��ȡ
			ByteBuffer[] bufs = {buf1,buf2};
			channle1.read(bufs);
			
			for(ByteBuffer buf : bufs){
				buf.flip();
			}
			
			System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
			System.out.println(new String(bufs[1].array(),0,bufs[1].limit()));
			
			
			//�ۼ�д��
			RandomAccessFile raf2 = new RandomAccessFile("2.txt","rw");
			FileChannel channle2 = raf2.getChannel();
			channle2.write(bufs);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

package com.javaBase.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



/**
 * 一  字符集 CharSet
 * @author feng
 *
 */
public class TestBuffer3 {
	
	public static void main1(String[] args) {
		Map<String, Charset> map = Charset.availableCharsets();
		Set<Entry<String, Charset>> set = map.entrySet();
		for(Entry<String, Charset> entry : set){
			System.out.println(entry.getKey()+"---"+entry.getValue());
		}
		
	}
	
	public static void main(String[] args) {
		Charset cs1 = Charset.forName("GBK");
		CharsetEncoder en = cs1.newEncoder();
		CharsetDecoder de = cs1.newDecoder();
		
		CharBuffer cb = CharBuffer.allocate(1024);
		cb.put("营养快线");
		cb.flip();
		
		
		try {
			//编码
			ByteBuffer enBuf = en.encode(cb);
			while(enBuf.hasRemaining()){
				System.out.println(enBuf.get());
			}
			
			//解码
			enBuf.flip();
			CharBuffer deBuf = de.decode(enBuf);
			System.out.println(deBuf.toString());
			
			
		} catch (CharacterCodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}

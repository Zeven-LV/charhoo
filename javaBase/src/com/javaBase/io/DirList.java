package com.javaBase.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

public class DirList {
	
	public static void main(String[] args) {
		
//		fileList();
//		fileCompareTo();
//		fileList2("123");
	}
	
	public static void fileList(){
		File file = new File(".");
		for( String str : file.list()){
			System.out.println(str);
		}
	}
	
	public static void fileCompareTo(){
		File file = new File(".");
		System.out.println(file.getPath());
		File file1 = new File(".");
		System.out.println(file1.getPath());
		File file2 = new File("./src/");
		System.out.println(file2.getPath());
		File file3 = new File("../JavaTest/");
		System.out.println(file3.getPath());
//		file.compareTo(file1)  == file.getPath().compareToIgnoreCase(feil1.getPath())
		System.out.println(file.compareTo(file1));
		System.out.println(file.compareTo(file2));
		System.out.println(file.compareTo(file3));
	}
	
	
	public static void fileList2(String name){
		File file = new File(".");
		for( String str : file.list(filter(name))){
			System.out.println(str);
		}
	}

		
	public static FilenameFilter filter(final String str){
		return new FilenameFilter() {
			String fn = str;
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				String f = new File(name).getName();
				return f.indexOf(fn) != -1;
			}
		};
		
	}
	
	

}

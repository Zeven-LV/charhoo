package com.javaBase.collection;

import java.util.Vector;

/**
 * Vector
 * Ĭ�Ϲ��캯������һ������Ϊ10������Ϊ0�Ķ������顣
 * @author feng
 *
 */
public class SortVector extends Vector{
	
	private Comparable comparable;
	
	public void sort(){
		quickSort(0,size() - 1);
	}
	
	public void quickSort(int left, int right){
		if(right > left){
			Object o1 = elementAt(right);
		      int i = left - 1;
		      int j = right;
		      Object o = null;
		      while(true) {
//		        while(comparable.compareTo(o).lessThan(elementAt(++i), o1)){
//		        	
//		        };
//		        while(j > 0)
//		          if(comparable.compareTo(o).lessThanOrEqual(elementAt(--j), o1))
//		            break; // out of while
//		        if(i >= j) break;
//		        swap(i, j);
		      }
		}
		
	}
	
	public void swap(int i, int j){
		
	}
	
	

}

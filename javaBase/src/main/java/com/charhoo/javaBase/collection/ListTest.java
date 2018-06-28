package com.charhoo.javaBase.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * ArrayList,Object[]，
 * LinkList,Node<>,insert和remove操作效率高，可用来实现stack，Queue
 * Vector
 * @author feng
 *
 */
public class ListTest {
	
	public static void main(String[] args) {
		/**
		 * 遍历，for：get，iterator：next
		 */
		List<String> array = new ArrayList<String>();
		/**
		 * 对first和last的操作
		 */
		List<String> link = new LinkedList<String>();
        /**
         * 线程安全的，通过synchronized方法实现
         *
         */
		Vector<String> vector = new Vector<String>();
	}

}

package com.javaBase.collection;

import java.util.Iterator;
import java.util.function.Consumer;

public class AIterableTest implements Iterable{

	public static void main(String[] args) {
		AIterableTest test = new AIterableTest();
		test.forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				// TODO Auto-generated method stub
				System.out.println(t);
			}
		});
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}

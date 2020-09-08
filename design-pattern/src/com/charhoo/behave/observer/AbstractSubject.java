package com.charhoo.behave.observer;

import java.util.Enumeration;
import java.util.Vector;

public abstract class AbstractSubject implements Subject{

    /**
     * 事件收集器，用Vector 线程安全
     */
	private Vector<Observer> vector = new Vector<Observer>();
    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Enumeration<Observer> enumo = vector.elements();
        while(enumo.hasMoreElements()){
            enumo.nextElement().update();
        }
    }

}

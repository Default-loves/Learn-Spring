package com.junyi.enent;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * User: JY
 * Date: 2020/7/14 0014
 * Description: Java中的观察者模式
 * EventObservable-被观察者
 * EventObserver-观察者
 */
public class ObserverDemo {
    public static void main(String[] args) {
        EventObservable eventObservable = new EventObservable();
        eventObservable.addObserver(new EventObserver());
        eventObservable.notifyObservers("Hi, you~");

    }

    /**
     * 被观察者，即消息发送者
     */
    private static class EventObservable extends Observable {
        @Override
        public void setChanged() {
            super.setChanged();
        }
        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }

    }

    /**
     * 观察者
     */
    private static class EventObserver implements Observer, EventListener {

        @Override
        public void update(Observable o, Object event) {
            EventObject eventObject = (EventObject) event;
            System.out.println(eventObject);
        }
    }
}

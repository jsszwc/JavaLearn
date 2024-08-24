package org.example.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {

    public static void main(String[] args) {
        AtomicIntegerTest();
    }

    public static void AtomicIntegerTest() {
        AtomicInteger atomicInteger = new AtomicInteger(1);

        atomicInteger.getAndUpdate(x -> x * 2 + 5);
        //print 7
        System.out.println(atomicInteger.get());

        atomicInteger.getAndAccumulate(3, (x, y) -> x*2 + y);
        //print 17
        System.out.println(atomicInteger.get());

        //print 17
        System.out.println(atomicInteger.compareAndExchange(1, 2));
        //print 17
        System.out.println(atomicInteger.compareAndExchange(17, 2));
    }
}
package org.example.redisson;

import org.redisson.api.RAtomicDouble;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;

/**
 * 分布式原子类。还有RLongAdder、RDoubleAdder
 */
public class AtomicDemo {

    private static final RedissonClient redisson = RedissonUtil.getRedisson();

    public static void main(String[] args) {
        operateRAtomicLong();
        operateRAtomicDouble();
        redisson.shutdown();
    }

    public static void operateRAtomicLong() {
        RAtomicLong atomicLong = redisson.getAtomicLong("myAtomicLong");
        System.out.println(atomicLong.get());
        atomicLong.set(3);
        System.out.println(atomicLong.incrementAndGet());
        System.out.println(atomicLong.incrementAndGet());
        System.out.println(atomicLong.incrementAndGet());
        System.out.println(atomicLong.incrementAndGet());
    }

    public static void operateRAtomicDouble() {
        RAtomicDouble atomicDouble = redisson.getAtomicDouble("myAtomicDouble");
        atomicDouble.set(2.81);
        System.out.println(atomicDouble.addAndGet(4.11));
        System.out.println(atomicDouble.get());
    }
}

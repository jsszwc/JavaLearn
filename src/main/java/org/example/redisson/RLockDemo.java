package org.example.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

public class RLockDemo {

    private static final RedissonClient redisson = RedissonUtil.getRedisson();

    public static void main(String[] args) {
        operateLock();
        redisson.shutdown();
    }

    public static void operateLock() {
        //会在redis中生成一个临时对象，释放锁时会使用del命令删除
        RLock lock = redisson.getLock("myLock");
        try {
            if(lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println("get the lock");
                TimeUnit.SECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

package org.example.redisson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

/**
 * RBucket 是一个用于处理 Redis 中简单键值对的对象。它是 Redisson 提供的 API 中的一部分，用于操作 Redis 的 String 类型数据。
 * RBucket 是一个封装了 Redis 操作的高级抽象，简化了对 Redis String 类型数据的操作。
 * 提供了一些用于操作数据的方法，如设置值、获取值、删除键等，还支持设置过期时间、检查键是否存在等功能。
 */
public class RBucketDemo {

    public static void main(String[] args) {
        RBucketOperateString();
        RBucketOperateInteger();
        RBucketStoreComplexObject();
    }

    public static void RBucketOperateString() {
        RedissonClient redisson = RedissonUtil.getRedisson();

        RBucket<String> bucket = redisson.getBucket("key-str1");
        bucket.set("value1");

        //get-set，原子性命令
        System.out.println(bucket.getName() + ": " + bucket.getAndSet("value2"));
        System.out.println(bucket.getName() + ": " + bucket.get());

        bucket.delete();
        System.out.println(bucket.isExists());
        System.out.println(bucket.getExpireTime());
    }

    public static void RBucketOperateInteger() {
        RedissonClient redisson = RedissonUtil.getRedisson();
        RBucket<Integer> bucket = redisson.getBucket("key-int1");
        //注意由于RBucket的特点，在redis中key-int1的类型是String
        bucket.set(1);
        System.out.println(bucket.getName() + ": " + bucket.get());
    }

    public static void RBucketStoreComplexObject() {
        RedissonClient redisson = RedissonUtil.getRedisson();

        RBucket<Person> bucket = redisson.getBucket("person-Tom");
        bucket.set(new Person("Tom", 22));

        Person person = bucket.get();
        System.out.println(bucket.getName() + ": " + person);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Person {
        private String name;
        private int age;
    }
}

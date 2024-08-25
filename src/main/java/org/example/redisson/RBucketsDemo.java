package org.example.redisson;

import org.redisson.api.RBuckets;
import org.redisson.api.RedissonClient;

import java.util.HashMap;
import java.util.Map;

/**
 * 允许用户同时操作多个Redis中的RBucket，使用RBuckets可以执行批量的读取、写入、更新或删除操作，这些操作通常比单独对每个键进行操作更高效
 * 批量操作： RBuckets通过Redis的管道（pipelining）功能，将多个命令打包在一起用一次网络请求发送到 Redis 服务器。这样可以减少客户端和服务器之间的往返时间（RTT）
 * 原子性： 尽管 RBuckets 提供了批量操作的便利，但批量操作本身并不是原子性的。如果需要原子性操作，可以使用 Lua 脚本或 Redis 事务（使用 MULTI/EXEC 命令）。
 */
public class RBucketsDemo {

    public static void main(String[] args) {
        RedissonClient redisson = RedissonUtil.getRedisson();

        RBuckets buckets = redisson.getBuckets();
        //对应MGET命令
        Map<String, String> map = buckets.get("bucket1", "bucket2", "bucket3");
        System.out.println(map.size());
        map.forEach((k, v) -> System.out.println(k + "=" + v));

        Map<String, Object> set = new HashMap<>();
        set.put("bucket4", "someValue");
        set.put("bucket5", 12345);
        set.put("bucket6", 123456);
        //对应MSET命令
        buckets.set(set);

        Map<String, Object> newSet = new HashMap<>();
        newSet.put("bucket4", "value4");
        newSet.put("bucket8", "value8");
        //对应MSETNX命令，只要有一个key已存在就设置失败
        buckets.trySet(newSet);

        System.out.println("end...");
    }
}

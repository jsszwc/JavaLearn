package org.example.redisson;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

public class RMapDemo {

    private static final RedissonClient redisson = RedissonUtil.getRedisson();

    public static void main(String[] args) {
        operateRMap();
        redisson.shutdown();
    }

    /**
     * RMap的很多方法都有fast前缀版本，两者区别是前者返回之前的值，fast版本不返回之前的值，更快一些
     */
    public static void operateRMap() {
        RMap<String, String> map = redisson.getMap("anyMap");
        String prevObject = map.put("123", "value1");
        System.out.println(prevObject);
        String currentObject = map.putIfAbsent("323", "value1");
        System.out.println(currentObject);
        prevObject = map.remove("123");
        System.out.println(prevObject);
    }
}

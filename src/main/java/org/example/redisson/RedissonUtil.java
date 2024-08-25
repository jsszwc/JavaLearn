package org.example.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

public class RedissonUtil {

    private static volatile RedissonClient redisson;

    public static RedissonClient getRedisson() {
        if (redisson == null) {
            synchronized (RedissonUtil.class) {
                if (redisson == null) {
                    Config config = new Config();
                    //不设置编码规则，写到redis中的值会优点莫名其妙，不知道使用的什么编码规则
                    //StringCodec编码规则适用于简单字符串
                    //JsonJacksonCodec编码规则使用于对象
                    config.setCodec(new JsonJacksonCodec());
                    //单个redis服务器
                    config.useSingleServer()
                            .setAddress("redis://127.0.0.1:6379")
                            .setDatabase(0);
                    redisson = Redisson.create(config);
                }
            }
        }
        return redisson;
    }
}

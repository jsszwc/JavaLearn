package org.example.redisson;

import org.redisson.api.BatchOptions;
import org.redisson.api.BatchResult;
import org.redisson.api.RBatch;
import org.redisson.api.RedissonClient;

import java.util.List;

/**
 * RBatch使用pipeline一次发送多条命令
 */
public class RBatchDemo {

    private static final RedissonClient redisson = RedissonUtil.getRedisson();

    public static void main(String[] args) {
        operateRBatch();
        redisson.shutdown();
    }


    public static void operateRBatch() {
        RBatch batch = redisson.createBatch(BatchOptions.defaults());
        //RBatch的get*方法返回对应数据结构的异步对象，然后在异步对象上进行操作即可
        batch.getMap("batch1").fastPutAsync("1", "2");
        batch.getMap("batch2").fastPutAsync("2", "3");
        batch.getMap("batch3").fastPutAsync("2", "5");
        //原子性处理key
        batch.getBucket("batch4").getAndSetAsync(0);
        batch.getMap("batch5").fastPutAsync("3", "3");
        batch.getMap("batch6").fastPutAsync("4", "5");
        batch.getBucket("batch7").getAndSetAsync(2);

        BatchResult res = batch.execute();
        //有几个操作就有几个返回值，且返回值一定按操作顺序
        List<?> list = res.getResponses();
        System.out.println(list.size());
    }
}

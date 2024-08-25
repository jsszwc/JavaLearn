package org.example.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class CaffeineDemo {

    public static void main(String[] args) {
        operateCaffeine();
    }

    public static void operateCaffeine() {
        Cache<String, String> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build();

        LoadingCache<String, String> loadingCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public @Nullable String load(String s) throws Exception {
                        System.out.println("Loading: " + s);
                        return s + s;
                    }

                    @Override
                    public Map<? extends String, ? extends String> loadAll(Set<? extends String> keys) throws Exception {
                        Map<String, String> map = new HashMap<>();
                        keys.forEach(key -> {
                            map.put(key, key.toString() + key.toString());
                        });
                        return map;
                    }
                });

        System.out.println(loadingCache.get("111"));
        loadingCache.invalidate("111");
        System.out.printf(loadingCache.get("111"));
    }
}

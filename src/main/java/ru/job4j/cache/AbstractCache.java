package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class AbstractCache<K, V> {
    private final Map<K, SoftReference<V>> cache = new HashMap<>();
    int count;

    public final void put(K key, V value) {
        SoftReference<V> string = new SoftReference<>(value);
        cache.put(key, string);
        if (++count % 100 == 0) {
            cache.entrySet().removeIf(entry -> entry.getValue().get() == null);
        }
    }

    public final V get(K key) {
        SoftReference<V> ref = cache.get(key);
        V result = (ref != null) ? ref.get() : null;
        if (result != null) {
            return result;
        }
        result = load(key);
        cache.put(key, new SoftReference<>(result));

        return result;
    }

    protected V load(K key) {
        return null;
    }
}

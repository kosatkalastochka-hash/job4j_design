package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        if (containsKeyByIndex(key, index)) {
            return table[index].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int index = getIndex(key);
        if (containsKeyByIndex(key, index)) {
            table[index].key = null;
            table[index].value = null;
            table[index] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private boolean containsKeyByIndex(K key, int index) {
        return table[index] != null && hashCode(table[index].key) == hashCode(key) && Objects.equals(table[index].key, key);
    }

    private int getIndex(K key) {
        return indexFor(hash(hashCode(key)));
    }

    private int hashCode(K key) {
        return Objects.hashCode(key);
    }

    private int hash(int hashCode) {
        int h = hashCode;
        return (h == 0) ? 0 : h ^ (h >>> 16);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        int newCapacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[newCapacity];
        for (K currentKey : this) {
            V currentValue = get(currentKey);
            int index = hash(hashCode(currentKey)) % newCapacity;
            if (newTable[index] == null) {
                newTable[index] = new MapEntry<>(currentKey, currentValue);
            }
        }
        table = newTable;
        capacity = newCapacity;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}




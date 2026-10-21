package dev.jaderss.algorithms.linkedlist;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class LruCache<K, V> {

    private static final class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> previous;
        private Entry<K, V> next;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<K, Entry<K, V>> entries = new HashMap<>();
    private final Entry<K, V> newest = new Entry<>(null, null);
    private final Entry<K, V> oldest = new Entry<>(null, null);

    public LruCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
        newest.next = oldest;
        oldest.previous = newest;
    }

    public Optional<V> get(K key) {
        Entry<K, V> entry = entries.get(key);
        if (entry == null) {
            return Optional.empty();
        }
        unlink(entry);
        linkAsNewest(entry);
        return Optional.of(entry.value);
    }

    public void put(K key, V value) {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(value, "value");
        Entry<K, V> entry = entries.get(key);
        if (entry != null) {
            entry.value = value;
            unlink(entry);
        } else {
            if (entries.size() == capacity) {
                Entry<K, V> evicted = oldest.previous;
                unlink(evicted);
                entries.remove(evicted.key);
            }
            entry = new Entry<>(key, value);
            entries.put(key, entry);
        }
        linkAsNewest(entry);
    }

    public int size() {
        return entries.size();
    }

    private void unlink(Entry<K, V> entry) {
        entry.previous.next = entry.next;
        entry.next.previous = entry.previous;
    }

    private void linkAsNewest(Entry<K, V> entry) {
        entry.previous = newest;
        entry.next = newest.next;
        newest.next.previous = entry;
        newest.next = entry;
    }
}

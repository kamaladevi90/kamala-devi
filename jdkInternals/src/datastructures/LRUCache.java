package datastructures;
import java.util.*;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        // accessOrder = true enables LRU order
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // Remove the least recently used item if size exceeds capacity
        return size() > capacity;
    }

    // For testing
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "A"); // {1=A}
        cache.put(2, "B"); // {1=A, 2=B}
        cache.put(3, "C"); // {1=A, 2=B, 3=C}
        cache.get(1);      // {2=B, 3=C, 1=A}
        cache.put(4, "D"); // {3=C, 1=A, 4=D} — 2=B is evicted

        System.out.println(cache); // Output: {3=C, 1=A, 4=D}
    }
}

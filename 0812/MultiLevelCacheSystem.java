
import java.util.*;

public class MultiLevelCacheSystem {

    static class Entry implements Comparable<Entry> {

        int key;
        String value;
        int freq;
        long timestamp;

        Entry(int k, String v, int f, long t) {
            key = k;
            value = v;
            freq = f;
            timestamp = t;
        }

        public int compareTo(Entry other) {
            if (this.freq != other.freq) {
                return this.freq - other.freq;
            }
            return Long.compare(this.timestamp, other.timestamp);
        }
    }

    static class CacheLevel {

        int capacity, cost;
        PriorityQueue<Entry> heap;
        Map<Integer, Entry> map;

        CacheLevel(int c, int cost) {
            capacity = c;
            this.cost = cost;
            heap = new PriorityQueue<>();
            map = new HashMap<>();
        }

        boolean contains(int key) {
            return map.containsKey(key);
        }

        Entry get(int key) {
            return map.get(key);
        }

        void put(Entry e) {
            heap.offer(e);
            map.put(e.key, e);
        }

        void remove(Entry e) {
            map.remove(e.key);
            heap.remove(e);
        }

        boolean isFull() {
            return map.size() >= capacity;
        }

        Entry evict() {
            Entry e = heap.poll();
            if (e != null) {
                map.remove(e.key);
            }
            return e;
        }
    }

    CacheLevel L1 = new CacheLevel(2, 1);
    CacheLevel L2 = new CacheLevel(5, 3);
    CacheLevel L3 = new CacheLevel(10, 10);
    Map<Integer, Entry> allEntries = new HashMap<>();
    long time = 0;

    public String get(int key) {
        Entry e = allEntries.get(key);
        if (e == null) {
            return null;
        }
        e.freq++;
        e.timestamp = ++time;
        promote(e);
        return e.value;
    }

    public void put(int key, String value) {
        Entry e = allEntries.get(key);
        if (e != null) {
            e.value = value;
            e.freq++;
            e.timestamp = ++time;
            promote(e);
            return;
        }
        e = new Entry(key, value, 1, ++time);
        allEntries.put(key, e);
        placeInLevel(e);
    }

    private void promote(Entry e) {
        if (L1.contains(e.key)) {
            updateEntry(L1, e);
            return;
        }
        if (L2.contains(e.key)) {
            updateEntry(L2, e);
            if (shouldPromote(e, L2, L1)) {
                moveEntry(L2, L1, e);
            }
            return;
        }
        if (L3.contains(e.key)) {
            updateEntry(L3, e);
            if (shouldPromote(e, L3, L2)) {
                moveEntry(L3, L2, e);
            }
        }
    }

    private void updateEntry(CacheLevel level, Entry e) {
        level.heap.remove(e);
        level.heap.offer(e);
    }

    private boolean shouldPromote(Entry e, CacheLevel from, CacheLevel to) {
        return e.freq > from.cost && e.freq > to.cost;
    }

    private void moveEntry(CacheLevel from, CacheLevel to, Entry e) {
        from.remove(e);
        if (to.isFull()) {
            Entry evicted = to.evict();
            placeInLevel(evicted);
        }
        to.put(e);
    }

    private void placeInLevel(Entry e) {
        if (!L1.isFull()) {
            L1.put(e);
            return;
        }
        if (!L2.isFull()) {
            L2.put(e);
            return;
        }
        if (!L3.isFull()) {
            L3.put(e);
            return;
        }
        Entry evicted = L3.evict();
        allEntries.remove(evicted.key);
        L3.put(e);
    }

    public void printStatus() {
        System.out.print("L1: [");
        for (Entry e : L1.heap) {
            System.out.print(e.key + ",");
        }
        System.out.print("] L2: [");
        for (Entry e : L2.heap) {
            System.out.print(e.key + ",");
        }
        System.out.print("] L3: [");
        for (Entry e : L3.heap) {
            System.out.print(e.key + ",");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printStatus();
        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.printStatus();
        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.printStatus();
    }
}


import java.util.*;

public class MovingAverage {

    private int size;
    private Queue<Integer> window;
    private long sum;
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    private Map<Integer, Integer> delayed;
    private TreeMap<Integer, Integer> freqMap;

    public MovingAverage(int size) {
        this.size = size;
        window = new LinkedList<>();
        sum = 0;
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        delayed = new HashMap<>();
        freqMap = new TreeMap<>();
    }

    public double next(int val) {
        window.offer(val);
        sum += val;
        addNum(val);
        if (window.size() > size) {
            int out = window.poll();
            sum -= out;
            removeNum(out);
        }
        return (double) sum / window.size();
    }

    public double getMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        return ((double) maxHeap.peek() + minHeap.peek()) / 2;
    }

    public int getMin() {
        return freqMap.firstKey();
    }

    public int getMax() {
        return freqMap.lastKey();
    }

    private void addNum(int num) {
        freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    private void removeNum(int num) {
        freqMap.put(num, freqMap.get(num) - 1);
        if (freqMap.get(num) == 0) {
            freqMap.remove(num);
        }
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (num <= maxHeap.peek()) {
            if (num == maxHeap.peek()) {
                prune(maxHeap);
            }
        } else {
            if (num == minHeap.peek()) {
                prune(minHeap);
            }
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
            prune(maxHeap);
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
            prune(minHeap);
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.getOrDefault(heap.peek(), 0) > 0) {
            int num = heap.poll();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) {
                delayed.remove(num);
            }
        }
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(3);
        System.out.println(ma.next(1));
        System.out.println(ma.next(10));
        System.out.println(ma.next(3));
        System.out.println(ma.next(5));
        System.out.println(ma.getMedian());
        System.out.println(ma.getMin());
        System.out.println(ma.getMax());
    }
}

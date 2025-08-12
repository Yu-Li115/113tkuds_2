
import java.util.*;

public class SlidingWindowMedian {

    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    Map<Integer, Integer> delayed = new HashMap<>();
    int smallSize = 0, largeSize = 0;

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (maxHeap.isEmpty() || nums[i] <= maxHeap.peek()) {
                maxHeap.offer(nums[i]);
                smallSize++;
            } else {
                minHeap.offer(nums[i]);
                largeSize++;
            }
            balanceHeaps();
            if (i >= k) {
                int out = nums[i - k];
                delayed.put(out, delayed.getOrDefault(out, 0) + 1);
                if (out <= maxHeap.peek()) {
                    smallSize--;
                    if (out == maxHeap.peek()) {
                        prune(maxHeap);
                    }
                } else {
                    largeSize--;
                    if (out == minHeap.peek()) {
                        prune(minHeap);
                    }
                }
                balanceHeaps();
            }
            if (i >= k - 1) {
                if (k % 2 == 1) {
                    result[i - k + 1] = maxHeap.peek();
                } else {
                    result[i - k + 1] = ((double) maxHeap.peek() + minHeap.peek()) / 2;
                }
            }
        }
        return result;
    }

    private void balanceHeaps() {
        if (smallSize > largeSize + 1) {
            minHeap.offer(maxHeap.poll());
            smallSize--;
            largeSize++;
            prune(maxHeap);
        } else if (largeSize > smallSize) {
            maxHeap.offer(minHeap.poll());
            largeSize--;
            smallSize++;
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
        SlidingWindowMedian swm = new SlidingWindowMedian();
        int[] arr1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] arr2 = {1, 2, 3, 4};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(arr1, 3)));
        System.out.println(Arrays.toString(swm.medianSlidingWindow(arr2, 2)));
    }
}

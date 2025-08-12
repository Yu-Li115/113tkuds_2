
import java.util.PriorityQueue;
import java.util.Arrays;

public class MergeKSortedArrays {

    static class Element implements Comparable<Element> {

        int value;
        int arrayIndex;
        int elementIndex;

        Element(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        public int compareTo(Element other) {
            return this.value - other.value;
        }
    }

    public static int[] mergeKSortedArrays(int[][] arrays) {
        PriorityQueue<Element> minHeap = new PriorityQueue<>();
        int totalLength = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Element(arrays[i][0], i, 0));
                totalLength += arrays[i].length;
            }
        }
        int[] result = new int[totalLength];
        int index = 0;
        while (!minHeap.isEmpty()) {
            Element e = minHeap.poll();
            result[index++] = e.value;
            int nextIndex = e.elementIndex + 1;
            if (nextIndex < arrays[e.arrayIndex].length) {
                minHeap.offer(new Element(arrays[e.arrayIndex][nextIndex], e.arrayIndex, nextIndex));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] arr1 = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        int[][] arr2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] arr3 = {{1}, {0}};
        System.out.println(Arrays.toString(mergeKSortedArrays(arr1)));
        System.out.println(Arrays.toString(mergeKSortedArrays(arr2)));
        System.out.println(Arrays.toString(mergeKSortedArrays(arr3)));
    }
}


import java.util.ArrayList;

public class PriorityQueueWithHeap {

    static class Task {

        String name;
        int priority;
        long timestamp;

        Task(String name, int priority, long timestamp) {
            this.name = name;
            this.priority = priority;
            this.timestamp = timestamp;
        }
    }

    private ArrayList<Task> heap;
    private long timeCounter;

    public PriorityQueueWithHeap() {
        heap = new ArrayList<>();
        timeCounter = 0;
    }

    public void addTask(String name, int priority) {
        heap.add(new Task(name, priority, timeCounter++));
        heapifyUp(heap.size() - 1);
    }

    public String executeNext() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        Task top = heap.get(0);
        Task last = heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return top.name;
    }

    public String peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return heap.get(0).name;
    }

    public void changePriority(String name, int newPriority) {
        for (Task t : heap) {
            if (t.name.equals(name)) {
                t.priority = newPriority;
                rebuildHeap();
                return;
            }
        }
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (compare(heap.get(index), heap.get(parent)) > 0) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        int size = heap.size();
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;
            if (left < size && compare(heap.get(left), heap.get(largest)) > 0) {
                largest = left;
            }
            if (right < size && compare(heap.get(right), heap.get(largest)) > 0) {
                largest = right;
            }
            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        Task temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private int compare(Task a, Task b) {
        if (a.priority != b.priority) {
            return a.priority - b.priority;
        }
        return (int) (b.timestamp - a.timestamp);
    }

    private void rebuildHeap() {
        for (int i = (heap.size() - 2) / 2; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();
        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);
        System.out.println(pq.executeNext());
        System.out.println(pq.executeNext());
        System.out.println(pq.executeNext());
    }
}

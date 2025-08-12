
import java.util.*;

public class MeetingRoomScheduler {

    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.offer(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= heap.peek()) {
                heap.poll();
            }
            heap.offer(intervals[i][1]);
        }
        return heap.size();
    }

    public static int maxMeetingTimeWithRooms(int[][] intervals, int rooms) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        PriorityQueue<Integer> roomEndTimes = new PriorityQueue<>();
        for (int i = 0; i < rooms; i++) {
            roomEndTimes.offer(0);
        }
        int totalTime = 0;
        for (int[] interval : intervals) {
            int earliestEnd = roomEndTimes.poll();
            if (interval[0] >= earliestEnd) {
                totalTime += (interval[1] - interval[0]);
                roomEndTimes.offer(interval[1]);
            } else {
                roomEndTimes.offer(earliestEnd);
            }
        }
        return totalTime;
    }

    public static void main(String[] args) {
        System.out.println(minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        System.out.println(minMeetingRooms(new int[][]{{9, 10}, {4, 9}, {4, 17}}));
        System.out.println(minMeetingRooms(new int[][]{{1, 5}, {8, 9}, {8, 9}}));
        System.out.println(maxMeetingTimeWithRooms(new int[][]{{1, 4}, {2, 3}, {4, 6}}, 1));
    }
}

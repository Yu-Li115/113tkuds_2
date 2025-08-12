
import java.util.PriorityQueue;

public class StockMaximizer {

    public static int maxProfit(int[] prices, int K) {
        if (prices.length == 0 || K == 0) {
            return 0;
        }
        PriorityQueue<Integer> profits = new PriorityQueue<>((a, b) -> b - a);
        int i = 0, n = prices.length;
        while (i < n - 1) {
            while (i < n - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            int buy = i++;
            while (i < n && prices[i] >= prices[i - 1]) {
                i++;
            }
            int sell = i - 1;
            if (sell > buy) {
                profits.offer(prices[sell] - prices[buy]);
            }
        }
        int count = 0, totalProfit = 0;
        while (count < K && !profits.isEmpty()) {
            totalProfit += profits.poll();
            count++;
        }
        return totalProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{2, 4, 1}, 2));
        System.out.println(maxProfit(new int[]{3, 2, 6, 5, 0, 3}, 2));
        System.out.println(maxProfit(new int[]{1, 2, 3, 4, 5}, 2));
    }
}

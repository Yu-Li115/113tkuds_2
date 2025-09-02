// 題目：Container With Most Water
// 給定一個整數陣列 height，每個元素代表一條直線的高度。
// 請找出兩條直線與 x 軸形成的容器，能裝最多的水。

public class It_11_containerwithmostwater {

    public int maxArea(int[] height) {
        // 使用雙指針：一個從左邊開始，一個從右邊開始
        int left = 0, right = height.length - 1;
        int max = 0;

        // 當左右指針沒有交錯時持續檢查
        while (left < right) {
            // 計算容器高度：取左右邊較小的那一根
            int h = Math.min(height[left], height[right]);
            // 計算容器寬度：(right - left)
            int area = h * (right - left);
            // 更新最大值
            max = Math.max(max, area);

            // 移動較短的指針，因為高度受限於最小值
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        It_11_containerwithmostwater solution = new It_11_containerwithmostwater();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(solution.maxArea(height)); // 輸出：49
    }
}

/*
解題思路：
1. 題目要求找出兩條直線與 x 軸組成的容器，能盛最多的水。
2. 使用雙指針 (two pointers)：
   - 一開始分別指向陣列的最左與最右。
   - 每次計算當前容器的面積，並更新最大值。
   - 移動較短的指針，因為高度決定了容量上限。
3. 時間複雜度 O(n)，只需遍歷一次。
4. 空間複雜度 O(1)，僅用到變數。
*/

// 題目：Valid Sudoku
// 功能：判斷 9x9 數獨盤面是否有效（不需解題）

public class It_36_validSudoku {

    public boolean isValidSudoku(char[][] board) {
        // 檢查每一列
        for (int i = 0; i < 9; i++) {
            boolean[] seen = new boolean[9];
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int num = c - '1';
                    if (seen[num]) {
                        return false;
                    }
                    seen[num] = true;
                }
            }
        }

        // 檢查每一行
        for (int j = 0; j < 9; j++) {
            boolean[] seen = new boolean[9];
            for (int i = 0; i < 9; i++) {
                char c = board[i][j];
                if (c != '.') {
                    int num = c - '1';
                    if (seen[num]) {
                        return false;
                    }
                    seen[num] = true;
                }
            }
        }

        // 檢查每個 3x3 區塊
        for (int block = 0; block < 9; block++) {
            boolean[] seen = new boolean[9];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int row = 3 * (block / 3) + i;
                    int col = 3 * (block % 3) + j;
                    char c = board[row][col];
                    if (c != '.') {
                        int num = c - '1';
                        if (seen[num]) {
                            return false;
                        }
                        seen[num] = true;
                    }
                }
            }
        }

        return true;
    }

    // 測試用 main
    public static void main(String[] args) {
        It_36_validSudoku solution = new It_36_validSudoku();

        char[][] board1 = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(solution.isValidSudoku(board1)); // 預期 true

        char[][] board2 = {
            {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(solution.isValidSudoku(board2)); // 預期 false
    }
}

/*
解題思路：
1. 用布林陣列 seen[9] 追蹤每個數字是否出現過。
2. 依序檢查：
   - 每一列
   - 每一行
   - 每個 3x3 小方塊
3. 若有重複數字 → 回傳 false。
4. 全部檢查通過 → 回傳 true。

時間複雜度：O(9^2) ≈ O(1)（固定盤面大小）
空間複雜度：O(1)
*/

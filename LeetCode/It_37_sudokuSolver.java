// 題目：Sudoku Solver
// 功能：解 9x9 數獨，保證唯一解

import java.util.*;

public class It_37_sudokuSolver {

    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (backtrack(board)) {
                                return true;
                            }
                            board[i][j] = '.'; // 回溯
                        }
                    }
                    return false; // 沒有數字能放 → 回溯
                }
            }
        }
        return true; // 全部填完
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) {
                return false; // 行

                        }if (board[i][col] == c) {
                return false; // 列

                        }int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == c) {
                return false; // 區塊

                    }}
        return true;
    }

    // 測試用 main
    public static void main(String[] args) {
        It_37_sudokuSolver solver = new It_37_sudokuSolver();

        char[][] board = {
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

        solver.solveSudoku(board);

        // 輸出結果
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}

/*
解題思路：
1. 用回溯法 (DFS)：逐格填數字 1~9。
2. 若當前數字合法 (行/列/3x3 區塊皆不衝突)，則遞迴繼續。
3. 若填不下去 → 回溯。
4. 當整個棋盤填滿 → 找到唯一解。

時間複雜度：O(9^(空格數)) 最壞情況，但實際上剪枝效率高。
空間複雜度：O(1)（僅遞迴棧）
*/

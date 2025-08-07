
import java.util.*;

public class TicTacToeBoard {

    static char[][] board = new char[3][3];

    public static void main(String[] args) {
        init();
        place(0, 0, 'X');
        place(1, 1, 'O');
        place(0, 1, 'X');
        place(2, 2, 'O');
        place(0, 2, 'X');
        print();
        if (checkWin('X')) {
            System.out.println("X wins"); 
        }else if (checkWin('O')) {
            System.out.println("O wins"); 
        }else if (isDraw()) {
            System.out.println("Draw"); 
        }else {
            System.out.println("Game continues");
        }
    }

    static void init() {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(board[i], ' ');
        }
    }

    static boolean place(int r, int c, char ch) {
        if (r < 0 || r > 2 || c < 0 || c > 2 || board[r][c] != ' ') {
            return false;
        }
        board[r][c] = ch;
        return true;
    }

    static boolean checkWin(char ch) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == ch && board[i][1] == ch && board[i][2] == ch)
                    || (board[0][i] == ch && board[1][i] == ch && board[2][i] == ch)) {
                return true;
            }
        }
        return (board[0][0] == ch && board[1][1] == ch && board[2][2] == ch)
                || (board[0][2] == ch && board[1][1] == ch && board[2][0] == ch);
    }

    static boolean isDraw() {
        for (char[] row : board) {
            for (char c : row) {
                if (c == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    static void print() {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}

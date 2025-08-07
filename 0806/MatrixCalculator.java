
import java.util.*;

public class MatrixCalculator {

    public static void main(String[] args) {
        int[][] A = {{1, 2}, {3, 4}};
        int[][] B = {{5, 6}, {7, 8}};
        int[][] sum = new int[2][2];
        int[][] mul = new int[2][2];
        int[][] trans = new int[2][2];
        int max = A[0][0], min = A[0][0];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                sum[i][j] = A[i][j] + B[i][j];
                trans[j][i] = A[i][j];
                if (A[i][j] > max) {
                    max = A[i][j];
                }
                if (A[i][j] < min) {
                    min = A[i][j];
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    mul[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        System.out.println("加法：");
        print(sum);
        System.out.println("乘法：");
        print(mul);
        System.out.println("轉置：");
        print(trans);
        System.out.println("最大：" + max + " 最小：" + min);
    }

    static void print(int[][] m) {
        for (int[] r : m) {
            System.out.println(Arrays.toString(r));
        }
    }
}

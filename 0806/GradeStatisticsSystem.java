
import java.util.*;

public class GradeStatisticsSystem {

    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};
        int sum = 0, max = scores[0], min = scores[0];
        int[] levels = new int[5];
        for (int s : scores) {
            sum += s;
            if (s > max) {
                max = s;
            }
            if (s < min) {
                min = s;
            }
            if (s >= 90) {
                levels[0]++; 
            }else if (s >= 80) {
                levels[1]++; 
            }else if (s >= 70) {
                levels[2]++; 
            }else if (s >= 60) {
                levels[3]++; 
            }else {
                levels[4]++;
            }
        }
        double avg = sum * 1.0 / scores.length;
        int aboveAvg = 0;
        for (int s : scores) {
            if (s > avg) {
                aboveAvg++;
            }
        }
        System.out.println("平均：" + avg);
        System.out.println("最高：" + max);
        System.out.println("最低：" + min);
        System.out.println("A:" + levels[0] + " B:" + levels[1] + " C:" + levels[2] + " D:" + levels[3] + " F:" + levels[4]);
        System.out.println("高於平均：" + aboveAvg);
        System.out.println("成績報表：" + Arrays.toString(scores));
    }
}

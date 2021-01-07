package knowitjulekalender.J2020.luke16;

public class Luke16 {
    static int M = 1000000;
    static int[] sum = new int[M];

    public static void main(String[] args) {
        for (int i = 1; i < M; i++) {
            propagate_divisor(i);
        }

        int count = 0;
        for (int i = 0; i < M; i++) {

            if (sum[i] > i) {
                int dif = sum[i] - i;
                double sqrt = Math.sqrt(dif);
                if ((int) sqrt == sqrt) {
                    count++;
                }

            }
        }
        System.out.println("count: " + count);

    }

    public static void propagate_divisor(int divisor) {

        for (int i = 2 * divisor; i < M; i += divisor) {
            sum[i] += divisor;
        }
    }

}

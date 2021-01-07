package knowitjulekalender.J2020.luke14;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Luke14 {
    public static int m = 1800813;
    public static int primeCount = 0;
    public static Set<Integer> set = new HashSet<Integer>();
    public static ArrayList<Integer> list = new ArrayList<Integer>();

    public static void add(int a) {
        list.add(a);
        set.add(a);
    }

    public static void checkPrime(int n) {
        if (isPrime(n)) {
            primeCount++;
            // System.out.println(n + " is a prime");
        }
    }

    public static boolean isPrime(int n) {
        if (n < 2)
            return false;
        if (n == 2 || n == 3)
            return true;
        if (n % 2 == 0 || n % 3 == 0)
            return false;
        int sqrtN = (int) Math.sqrt(n) + 1;
        for (int i = 6; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        list.add(0);
        list.add(1);
        set.add(0);
        set.add(1);

        for (int i = 2; i <= m; i++) {
            int get = list.get(i - 2);
            int aMinus = get - i;
            if (aMinus >= 0 && !set.contains(aMinus)) {
                add(aMinus);
            } else {
                int aPlus = get + i;
                add(aPlus);
            }
        }
        for (int b : list) {
            checkPrime(b);
        }
        System.out.println(primeCount + "!");
    }
}
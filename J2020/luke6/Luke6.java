package knowitjulekalender.J2020.luke6;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class Luke6 {
    public static void main(String[] args) throws IOException {
        URL file = new URL("https://julekalender-backend.knowit.no/challenges/6/attachments/godteri.txt");
        Scanner sc = new Scanner(file.openStream());

        int sharedAmong = 127;

        String line = sc.nextLine();
        String[] list = line.split(",");
        int[] set = new int[list.length];

        int count = 0;
        for(int i = 0; i < set.length; i++){
            set[i] = Integer.parseInt(list[i]);
            count += set[i];
        }
        int m = count / sharedAmong;
        int maxLimit = m * sharedAmong;

        System.out.println("count: "+ count);
        System.out.println("m : "+m);
        System.out.println("maxLimit: "+maxLimit);

        
        printCombinations(set,-1,124965,new int[]{});

    }
    public static void printCombinations(int[] array, int pos, int sum, int[] acc) {
        if (Arrays.stream(acc).sum() == sum) {
            //System.out.println(Arrays.toString(acc));
            System.out.println("true");
        }
        for (int i = pos + 1; i < array.length; i++) {
            int[] newAcc = new int[acc.length + 1];
            System.arraycopy(acc, 0, newAcc, 0, acc.length);
            newAcc[acc.length] = array[i];
            printCombinations(array, i, sum, newAcc);
        }
    }
}


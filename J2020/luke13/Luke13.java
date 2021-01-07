package knowitjulekalender.J2020.luke13;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Luke13 {
    public static void main(String[] args) throws IOException {
        URL file = new URL("https://julekalender-backend.knowit.no/challenges/13/attachments/text.txt");
        Scanner sc = new Scanner(file.openStream());

        String line = sc.nextLine();

        int[] counter = new int[26];
        int index;
        String s = "";
        for (int i = 0; i < line.length() - 1; i++) {
            char c = line.charAt(i);
            index = ((int) c) - 97;
            counter[index] += 1;

            if (counter[index] == (index + 1)) {
                s += c;
            }
        }
        System.out.println(s);
    }
}
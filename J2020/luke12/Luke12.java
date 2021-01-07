package knowitjulekalender.J2020.luke12;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Luke12 {
    public static void main(String[] args) throws IOException {
        URL file = new URL("https://julekalender-backend.knowit.no/challenges/12/attachments/family.txt");
        Scanner sc = new Scanner(file.openStream());

        // 499506 length line
        String line = sc.nextLine();

        ArrayList<Integer> list = new ArrayList<Integer>();
        int level = 0;
        int max = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '(') {
                level++;

            }
            if (line.charAt(i) == ')') {
                if (line.charAt(i - 1) != ')') {
                    try {
                        int val = list.get(level) + 1;
                        if (val > max)
                            max = val;
                        list.set(level, val);
                    } catch (IndexOutOfBoundsException e) {

                    }
                }

                level--;
            }
            if (line.charAt(i) == ' ' && line.charAt(i - 1) != ')') {
                try {
                    int val = list.get(level) + 1;
                    if (val > max)
                        max = val;
                    list.set(level, val);
                } catch (IndexOutOfBoundsException e) {
                    list.add(1);
                }

            }
        }
        System.out.println("max: " + max);
    }
}
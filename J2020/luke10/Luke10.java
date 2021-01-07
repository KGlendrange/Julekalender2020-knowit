package knowitjulekalender.J2020.luke10;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Luke10 {
    public static void main(String[] args) throws IOException {
        URL file = new URL("https://julekalender-backend.knowit.no/challenges/10/attachments/leker.txt");
        Scanner sc = new Scanner(file.openStream());

        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineArray = line.split(",");
            for (int i = 0; i < lineArray.length; i++) {
                int oldVal = 0;
                if (hashMap.containsKey(lineArray[i])) {
                    // add one
                    oldVal = hashMap.get(lineArray[i]);
                }
                int newVal = lineArray.length - (i + 1);
                hashMap.put(lineArray[i], (oldVal + newVal));
            }
        }

        System.out.println("First place: ");
        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            if (maxEntry == null || maxEntry.getValue().compareTo(entry.getValue()) < 0) {
                maxEntry = entry;
            }
        }
        System.out.println(maxEntry.toString());
    }
}

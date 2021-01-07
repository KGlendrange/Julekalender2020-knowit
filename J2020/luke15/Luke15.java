package knowitjulekalender.J2020.luke15;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Luke15 {
    public static class Pair {
        public String first;
        public String second;

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) throws IOException {
        URL wlFile = new URL("https://julekalender-backend.knowit.no/challenges/15/attachments/wordlist.txt");
        Scanner scWl = new Scanner(wlFile.openStream());

        URL riddleFile = new URL("https://julekalender-backend.knowit.no/challenges/15/attachments/riddles.txt");
        Scanner scRiddle = new Scanner(riddleFile.openStream());

        ArrayList<String> wordList = new ArrayList<String>();

        while (scWl.hasNextLine()) {
            String line = scWl.nextLine();
            wordList.add(line);
        }

        ArrayList<Pair> riddleList = new ArrayList<Pair>();
        while (scRiddle.hasNextLine()) {
            String line = scRiddle.nextLine();
            String[] p = line.split(", ");
            String first = p[0];
            String second = p[1];
            Pair pair = new Pair(first, second);
            riddleList.add(pair);
        }

        // riddleList = new ArrayList<Pair>();
        // riddleList.add(new Pair("innovasjons", "løsheta"));
        // riddleList.add(new Pair("spektral", "sikringens"));
        // riddleList.add(new Pair("verdens", "spillet"));

        Set<String> totalWords = new HashSet<String>();
        for (Pair p : riddleList) {
            Set<String> firstWords = new HashSet<String>();
            Set<String> secondWords = new HashSet<String>();

            System.out.println("Pair: " + p.first + "-" + p.second);
            for (String l : wordList) {
                String rF = checkWords(l, p.first);
                if (rF.length() > 0) {
                    // System.out.println("first: " + rF);
                    firstWords.add(rF);

                }
                String rS = checkWords(l, p.second);
                if (rS.length() > 0) {
                    // System.out.println("second: " + rS);
                    secondWords.add(rS);

                }

            }
            firstWords.retainAll(secondWords);
            for (String b : firstWords) {
                System.out.println("common: " + b);
                boolean result = totalWords.add(b);
                if (!result)
                    System.out.println(b + " was already in totalwords");
            }
        }
        int count = 0;
        for (String b : totalWords) {
            if (wordList.contains(b)) {
                count += b.length();

                System.out.println("wordlist contains " + b + "-" + count);
            } else {
                System.out.println("wordlist does not contain " + b);
            }

        }
        System.out.println("count: " + count);

    }

    public static String checkWords(String l, String target) {
        if (l.length() != target.length()) {
            if (l.contains(target)) {
                int index = l.indexOf(target);
                if (index == 0)
                    return l.substring(target.length());
                if (index + target.length() == l.length()) {
                    return l.substring(0, index);
                }

            }
        }
        return "";
    }
}
package knowitjulekalender.J2020.luke11;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Luke11 {
    public static void main(String[] args) throws IOException {
        URL file = new URL("https://julekalender-backend.knowit.no/challenges/11/attachments/hint.txt");
        Scanner sc = new Scanner(file.openStream());

        String hint = "juletre";

        while (sc.hasNextLine()) {
            hint = sc.nextLine();
            checkHint(hint);
        }

    }

    public static void checkHint(String hint) {
        ArrayList<ArrayList<Character>> passwords = new ArrayList<ArrayList<Character>>();

        ArrayList<char[]> list = new ArrayList<char[]>();

        char[] arr = hint.toCharArray();
        list.add(arr);
        while (arr.length > 1) {
            arr = hintify(arr);
            list.add(arr);

        }
        for (int j = 0; j < hint.length(); j++) {
            ArrayList<Character> password = new ArrayList<Character>();
            for (int i = 0; i < list.size(); i++) {
                try {
                    password.add(list.get(i)[j]);

                } catch (IndexOutOfBoundsException e) {

                }
            }
            passwords.add(password);
        }
        for (int i = 0; i < passwords.size(); i++) {
            String s = getString(passwords.get(i));
            if (s.contains("eamqia")) {
                System.out.println("hint: " + hint);
            }
        }
    }

    public static String getString(ArrayList<Character> list) {
        StringBuilder builder = new StringBuilder(list.size());
        for (Character c : list) {
            builder.append(c);
        }
        return builder.toString();
    }

    public static char[] hintify(char[] arr) {
        if (arr.length == 1)
            return arr;
        char[] newArr = new char[arr.length - 1];
        for (int i = 1; i < arr.length; i++) {
            // a -> b
            int oldPoint = arr[i - 1] - 'a';

            int newPoint = arr[i] + 1 - 'a';

            // juletre -> uletre -> vmfusf -> j+v=e,u+m=g
            int newestPoint = ((newPoint + oldPoint) % 26) + 'a';
            newArr[i - 1] = (char) (newestPoint);
        }
        return newArr;
    }
}
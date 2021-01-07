package knowitjulekalender.J2020.luke7;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Luke7 {

    static int count = 0;

    public static void main(String[] args) throws IOException {
        URL file = new URL("https://julekalender-backend.knowit.no/challenges/7/attachments/forest.txt");
        Scanner sc = new Scanner(file.openStream());

        File file2 = new File(
                "C:/Users/krist/OneDrive/Skrivebord/vscode projects/knowitjulekalender/J2020/luke7/testForest.txt");
        Scanner sc2 = new Scanner(file2);

        int nLines = 35;
        int lineLength = 89702;

        // TEST
        // nLines = 25;
        // lineLength = 42;
        // also change scanner to sc2

        char[][] trees = new char[nLines][lineLength];

        int i = 0;
        while (sc.hasNextLine()) {
            char[] line = sc.nextLine().toCharArray();
            trees[i] = line;
            i++;
        }
        sc.close();
        sc2.close();

        // makes a list of all tree stems
        ArrayList<Integer> indexList = new ArrayList<Integer>();
        for (int x = 0; x < trees[trees.length - 1].length; x++) {
            if (trees[trees.length - 1][x] == '#') {
                indexList.add(x);

            }
        }
        System.out.println("trees: " + indexList.size());

        int firstIndex = indexList.get(0);
        int firstArea = (firstIndex * 2) + 1;
        int firstSize = checkSize(trees, firstIndex, firstArea);

        if (checkTree(trees, firstIndex, firstSize))
            count++;
        for (int t = 1; t < indexList.size(); t++) {
            int tree = t;
            int index = indexList.get(tree);
            int searchArea = index - indexList.get(tree - 1);

            int size = checkSize(trees, index, searchArea);

            System.out.println("Checking tree# " + (tree + 1));
            if (checkTree(trees, index, size))
                count++;

        }
        System.out.println("total " + count);

    }

    public static int checkSize(char[][] trees, int index, int searchArea) {
        for (int a = 1; a < searchArea; a++) {
            boolean flag = true;
            for (int b = trees.length - 1; b >= 0; b--) {
                // System.out.println((b+1)+"-"+(index+a));

                if (trees[b][index + a] == '#') {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                int test = (index + a) - index;
                return (test * 2) - 1;
            }
            ;
        }
        return -1;
    }

    public static boolean checkTree(char[][] trees, int index, int size) {
        // System.out.println("checking tree at "+trees.length+"-"+index+" with size
        // "+size);
        boolean missingTrunk = false;
        for (int i = trees.length - 1; i >= 0; i--) {

            if (trees[i][index] != '#') {
                // System.out.println("missing tree trunk at "+(i+1));
                missingTrunk = true;

                for (int y = 1; y < (size / 2); y++) {
                    if (trees[i][index + y] == '#' || trees[i][index - y] == '#') {
                        System.out.println("The top is broken");
                        return false;
                    }
                }
            } else {
                // if it previously didnt find the trunk and now it does, we have a broken trunk
                // return false
                if (missingTrunk) {
                    System.out.println("---------MISSING tree trunk");
                    return false;
                }
                for (int y = 1; y <= (size / 2); y++) {

                    if (!(trees[i][index + y] == trees[i][index - y])) {
                        System.out.println((i + 1) + "-" + (index - y) + ":(" + trees[i][index - y] + ") VS ("
                                + trees[i][index + y] + "):" + (i + 1) + "-" + (index + y));
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

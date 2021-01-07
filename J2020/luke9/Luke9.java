package knowitjulekalender.J2020.luke9;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Luke9 {
    public static void main(String[] args) throws IOException {
        URL file = new URL("https://julekalender-backend.knowit.no/challenges/9/attachments/elves.txt");
        Scanner sc = new Scanner(file.openStream());

        char[][] matrix = new char[200][200];
        int i = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            matrix[i] = line.toCharArray();
            i++;
        }

        char[][] test = { { 'F', 'F', 'F', 'S', 'F' }, { 'F', 'S', 'F', 'F', 'F' }, { 'F', 'S', 'F', 'S', 'F' },
                { 'S', 'F', 'F', 'S', 'F' }, { 'F', 'S', 'F', 'F', 'F' }, };

        System.out.println(runSimulation(matrix));
    }

    public static int runSimulation(char[][] matrix) {
        boolean done = false;
        int days = 1;
        while (!done) {
            matrix = runDay(matrix);
            // printMatrix(matrix);
            if (matrix[0][0] == 'Q') {
                return days;
            }
            days++;
        }

        return -1;
    }

    public static char[][] runDay(char[][] matrix) {
        char[][] newArray = new char[matrix.length][matrix[0].length];

        boolean changes = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                newArray[i][j] = matrix[i][j];
                if (matrix[i][j] == 'F') {
                    if (isAffected(matrix, i, j)) {
                        newArray[i][j] = 'S';
                        changes = true;
                    }
                }

            }
        }
        // if there was no changes done, give a sentinel value Q
        if (!changes) {
            newArray[0][0] = 'Q';
        }
        return newArray;
    }

    public static void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("-----");
    }

    // if a cell has two neighbors that are affected
    public static boolean isAffected(char[][] matrix, int x, int y) {
        int[] xDir = { 1, -1, 0, 0 };
        int[] yDir = { 0, 0, 1, -1 };
        int count = 0;
        for (int i = 0; i < xDir.length; i++) {
            try {
                if (matrix[x + xDir[i]][y + yDir[i]] == 'S') {
                    count++;
                    if (count >= 2)
                        return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // System.out.println("out of bounds at " + x + xDir[i] + "-" + y + yDir[i]);
            }
        }
        return false;

    }
}
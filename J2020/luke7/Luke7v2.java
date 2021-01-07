package knowitjulekalender.J2020.luke7;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Luke7v2 {
    static int count = 0;
    public static void main(String[] args) throws IOException {
        URL file = new URL("https://julekalender-backend.knowit.no/challenges/7/attachments/forest.txt");
        Scanner sc = new Scanner(file.openStream());

        char[][] arr = new char[35][89702];
        int x = 0;
        while(sc.hasNextLine()){
            char[] line = sc.nextLine().toCharArray();
            arr[x] = line;
            x++;
        }
        //find index of trunk
        ArrayList<Integer> indexList = new ArrayList<Integer>();
        for(int i = 0; i < arr[arr.length-1].length; i++){
            if(arr[arr.length-1][i] == '#'){
                indexList.add(i);
            }
        }

        char[][] newArray = new char[89702][35];
        for(int i=0; i<arr[0].length; i++){
            for(int j=arr.length-1; j>=0; j--){
                newArray[i][j] = arr[j][i];
            }
        }
        

        for(int a = 0; a < 89702; a++){
            int index = indexList.get(a);
            int i = index;
            System.out.println("i : "+ i);
            boolean clearLine = false;
            while(clearLine == false){

                clearLine = true;
                for(int j = 0; j < 35; j++){
                    
                    if(newArray[i][j] != ' ') {
                        clearLine = false;
                        break;
                    }
                }
                i++;
            }
            //Found a clear line
            int clearNumber = (i-1);
            System.out.println("clear line on " + clearNumber);
            int size = ((clearNumber-indexList.get(a))*2)-1;
            System.out.println("size: "+ size);
            if(f(newArray,index,size)) count++;

            System.out.println("count: "+ count);
        }
    }
    public static boolean f(char[][] newArray,int index, int size){
        
        
        for(int n = 0; n < (size/2); n++){
            String part1 = String.valueOf(newArray[index+n]);
            String part2 = String.valueOf(newArray[index-n]);
            if(!(part1.equals(part2))){
                System.out.println(part1 + " NOT "+part2);
                return false;
            }
        }
        return true;
    }
}

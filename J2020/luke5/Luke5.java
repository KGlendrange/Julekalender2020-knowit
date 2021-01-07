package knowitjulekalender.J2020.luke5;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Luke5 {

    public static void main(String[] args) throws IOException {
        URL file = new URL("https://julekalender-backend.knowit.no/challenges/5/attachments/rute.txt");
        Scanner sc = new Scanner(file.openStream());

        System.out.println(computeArea(sc.nextLine().toCharArray()));
    }

    public static int computeArea(char[] directions){
        int area = 0; 
        int y = 0;

        for(char dir : directions){
            switch(dir){
                case 'V': area+=y; break;
                case 'H': area-=y; break;
                case 'O': y-=1; break;
                case 'N': y+=1; break;
            }
        }
        return area <0 ? -area: area;
    }
}

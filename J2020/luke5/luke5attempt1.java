package knowitjulekalender.J2020.luke5;

import java.io.IOException;
import java.net.URL;

import java.util.Scanner;

public class luke5attempt1 {
    public enum Direction{
        Up,Down,Left,Right;
    }
    public static int computeArea(Direction[] directions){
        int xMax = Integer.MIN_VALUE;
        int xMin = Integer.MAX_VALUE;
        int x = 0;
        int leftmostIndex = 0;

        for(int i = 0; i < directions.length; i++) {
            Direction dir = directions[i];
            int dif = (dir == Direction.Left ? -1 : dir == Direction.Right? 1 : 0);
            x = x + dif;
            xMax = x > xMax ? x : xMax;
            if(x < xMin){
                xMin = x;
                leftmostIndex = (i + 1) % directions.length;
                
            }
        }
        MaxHeap[] heaps = new MaxHeap[xMax - xMin];
		for (int i = 0; i < heaps.length; i++) heaps[i] = new MaxHeap(4);

        
        x = 0; int y = 0; int index = leftmostIndex;
        

        do{
            Direction dir = directions[index];
            switch(dir){
                case Left: heaps[--x].add(y); break;
                case Right: heaps[x++].add(y); break;
                case Up: y++; break;
                case Down: y--; break;
            }
            index = (index+1) % directions.length;
        }while(index != leftmostIndex);

        //calculate area
        int area = 0;
        for(MaxHeap heap : heaps){
            while(!heap.isEmpty()){
                int max = heap.popMax();
                int min = heap.popMax();
                area += max-min;
            }
        }
        return area;

    }
    public static void main(String[] args) throws IOException {
        URL file = new URL("https://julekalender-backend.knowit.no/challenges/5/attachments/rute.txt");
        Scanner sc = new Scanner(file.openStream());

        String line = sc.nextLine();

        Direction[] input = new Direction[line.length()];
        for(int i = 0; i < line.length(); i++){
            char c = line.charAt(i);
            if(c == 'V') input[i] = Direction.Left;
            if(c == 'H') input[i] = Direction.Right;
            if(c == 'O') input[i] = Direction.Up;
            if(c == 'N') input[i] = Direction.Down; 

        }
  
        System.out.println(computeArea(input));
    }

    private static class MaxHeap {
		private int[] data;
		private int size;
		private int maxSize;
 
		public MaxHeap(int maxSize) {
			this.maxSize = maxSize;
			this.data = new int[maxSize];
			this.size = 0;
		}
 
		public void add(int value) {
			if (size == maxSize) {
				int[] oldData = data;
				maxSize *= 2;
				data = new int[maxSize];
				System.arraycopy(oldData, 0, data, 0, size);
			}
 
			// bubble up
			int index = size;
			while (index > 0  && data[parent(index)] < value) {
				data[index] = data[parent(index)];
				index = parent(index);
			}
			data[index] = value;
 
			size++;
		}
 
		public int popMax() {
			assert(size > 0);
 
			int max = data[0];
 
			size--;
			int value = data[size];
 
			// bubble down
			int index = 0;
			int largerChild;
			while (left(index) < size) {
				if (right(index) < size) {
					largerChild = data[left(index)] > data[right(index)]
									? left(index)
									: right(index);
				} else {
					largerChild = left(index);
				}
 
				if (value > data[largerChild]) break;
 
				data[index] = data[largerChild];
				index = largerChild;
			}
			data[index] = value;
 
			return max;
		}
 
		public boolean isEmpty() {
			return size == 0;
		}
 
		private static int parent(int i) {
			return (i - 1) / 2;
		}
 
		private static int left(int i) {
			return 2 * i + 1;
		}
 
		private static int right(int i) {
			return 2 * i + 2;
		}
	}
}
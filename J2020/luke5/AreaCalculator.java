package knowitjulekalender.J2020.luke5;
 
class AreaCalculator
{
	public static void main (String[] args)	{
		//  _
        // | |_
        // |_ _ |
		Direction[] input = { Direction.Up, Direction.Up, 
							  Direction.Right, Direction.Down,
							  Direction.Right, Direction.Down,
							  Direction.Left, Direction.Left };
 
		System.out.println(computeArea(input));
	}
 
	private static int computeArea(Direction[] directions) {
		int x = 0, xMin = Integer.MAX_VALUE, xMax = Integer.MIN_VALUE;
		int leftmostIndex = 0;
		for (int i = 0; i < directions.length; i++) {
			Direction direction = directions[i];
			x = x + (direction == Direction.Left ? -1 : direction == Direction.Right ? 1 : 0);
			xMax = x > xMax ? x : xMax;
			if (x < xMin) {
				xMin = x;
				leftmostIndex = (i + 1) % directions.length;
			}
		}
 
		MaxHeap[] heaps = new MaxHeap[xMax - xMin];
		for (int i = 0; i < heaps.length; i++) heaps[i] = new MaxHeap(4);
 
		x = 0;
		int y = 0;
        int index = leftmostIndex;
        
        System.out.println("leftMostIndex: "+leftmostIndex);
 
		do {
            Direction direction = directions[index];
			switch (direction) {
				case Left: heaps[--x].add(y); break;
				case Right: heaps[x++].add(y); break;
				case Up: y++; break;
				case Down: y--; break;
			}
			index = (index + 1) % directions.length;
		} while (index != leftmostIndex);
 
		int area = 0;
		for (MaxHeap heap : heaps) {
			while (!heap.isEmpty()) {
				area += heap.popMax() - heap.popMax();
			}
		}
 
		return area;
	}
 
	enum Direction {
		Left,
		Right,
		Up,
		Down
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
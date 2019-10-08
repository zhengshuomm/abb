package airbnb2019;

public class PourWater {
	public static void main(String[] args) {
		int[] heights = {5,4,2,1,2,3,2,1,0,1,2,4};
		pourWater(heights, 8, 5);
	}

	public static void pourWater(int[] heights, int water, int location) {
		int[] waters = new int[heights.length];
		while (water > 0) {

			int leftBest = location;
			int rightBest = location;
			for (int d = -1; d <= 1; d += 2) {
				int i = location + d;
				while (i >= 0
						&& i < waters.length
						&& heights[i] + waters[i] <= heights[i - d]
								+ waters[i - d]) {
					if (d == -1)
						leftBest = i;
					else
						rightBest = i;
					i += d;
				}
			}
			if (heights[leftBest] + waters[leftBest] < heights[location]
					+ waters[location])
				waters[leftBest]++;
			else if (heights[rightBest] + waters[rightBest] < heights[location]
					+ waters[location])
				waters[rightBest]++;
			else if (heights[leftBest] + waters[leftBest] <= heights[rightBest]
					+ waters[rightBest])
				waters[leftBest]++;
			else
				waters[rightBest]++;
			water--;
			print(heights, waters);
		}
	}

	public static void print(int[] heights, int[] waters) {
		int n = heights.length;
		int maxHeight = 0;
		for (int i = 0; i < n; i++) {
			maxHeight = Math.max(maxHeight, heights[i] + waters[i]);
		}
		for (int height = maxHeight; height >= 0; height--) {
			for (int i = 0; i < n; i++) {
				if (height <= heights[i]) {
					System.out.print("+");
				} else if (height > heights[i]
						&& height <= heights[i] + waters[i]) {
					System.out.print("W");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}

package airbnb2019;

public class HilbertCurve {
	public static void main(String args[]) {
		System.out.println(hilbertCurve(1, 1, 2));
		System.out.println(hilbertCurve(0, 1, 1));
		System.out.println(hilbertCurve(2, 2, 2));
	}

	private static int hilbertCurve(int x, int y, int iter) {
		if (iter == 0) {
			return 1;
		}
		int count = 1 << (2 * (iter - 1));
		int len = 1 << (iter - 1);
		if (x >= len && y >= len) {
			return 2 * count + hilbertCurve(x - len, y - len, iter - 1);
		} else if (x < len && y >= len) {
			return count + hilbertCurve(x, y - len, iter - 1);
		} else if (x < len && y < len) {
			return hilbertCurve(y, x, iter - 1);
		} else {
			return 3 * count
					+ hilbertCurve(len - 1 - y, 2 * len - 1 - x, iter - 1);
		}
	}
}

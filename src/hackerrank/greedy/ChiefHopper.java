package hackerrank.greedy;

import java.util.Scanner;

public class ChiefHopper {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)){
			int N = in.nextInt();
			int[] h = new int[N];
			for (int i = 0; i < N; i++) {
				h[i] = in.nextInt();
			}
			System.out.println(minStartingPower(h));
		}
	}
	
	public static int minStartingPower(int[] h) {
		int b = 0;
		for (int i = h.length-1; i >= 0; i--) {
			if ((b + h[i]) % 2 == 0) {
				b = (b + h[i]) / 2;
			} else {
				b = (b + h[i] + 1) / 2;
			}
		}
		return b;
	}

}

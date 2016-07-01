package hackerrank.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class GreedyFlorist {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int N = in.nextInt();
			int K = in.nextInt();
			int[] f = new int[N];
			for (int i = 0; i < N; i++) {
				f[i] = in.nextInt();
			}
			System.out.println(minCost(f, K));
		}
	}
	
	public static int minCost(int[] f, int K) {
		Arrays.sort(f);
		int sum = 0;
		for (int i = f.length-1; i >= 0; i--) {
			int multiplier = 1 + (f.length - 1 - i) / K;
			sum += multiplier * f[i];
		}
		return sum;
	}

}

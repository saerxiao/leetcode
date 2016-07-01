package hackerrank.dp;

import java.util.Scanner;

public class StockMaximize {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			for (int t = 0; t < T; t++) {
				int N = in.nextInt();
				int[] A = new int[N];
				for (int i = 0; i < N; i++) {
					A[i] = in.nextInt();
				}
				System.out.println(maxProfit(A));
			}
		}
	}
	
	public static long maxProfit(int[] A) {
		int maxInFuture = A[A.length - 1];
		long profit = 0;
		for (int i = A.length - 2; i >= 0; i--) {
			maxInFuture = Math.max(maxInFuture, A[i]);
			profit += Long.valueOf(maxInFuture - A[i]);
		}
		return profit;
	}

}

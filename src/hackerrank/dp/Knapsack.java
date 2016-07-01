package hackerrank.dp;

import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;

public class Knapsack {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			for (int t = 0; t < T; t++) {
				int N = in.nextInt();
				int K = in.nextInt();
				int[] A = new int[N];
				for (int i = 0; i < N; i++) {
					A[i] = in.nextInt();
				}
				System.out.println(nearestSum(A, K));
			}
		}
	}
	
	public static int nearestSum(int[] A, int K) {
		boolean[] hasSum = new boolean[K + 1];
		hasSum[0] = true;
		
		Arrays.sort(A);
		for (int k = 1; k <= K ; k++) {
			for (int i = 0; i < A.length; i++) {
				if (A[i] > k) break;
				hasSum[k] = hasSum[k] | hasSum[k - A[i]];
				if (hasSum[k]) break;
			}
		}
		
		for (int k = K; k >= 0; k--) {
			if (hasSum[k]) return k;
		}
		
		return 0;
	}

	@Test
	public void test() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.println(i + " " + j);
				if (j == 2) {
					break;
				}
			}
		}
	}
}

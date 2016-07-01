package hackerrank.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class MaxMin {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int N = in.nextInt();
			int K = in.nextInt();
			int[] A = new int[N];
			for (int i = 0; i < N; i++) {
				A[i] = in.nextInt();
			}
			System.out.println(maxMin(A, K));
		}
	}
	
	public static int maxMin(int[] A, int K) {
		Arrays.sort(A);
		int minDiff = A[K - 1] - A[0];
		for (int i = K; i < A.length; i++) {
			minDiff = Math.min(minDiff, A[i] - A[i-K+1]);
		}
		return minDiff;
	}

}

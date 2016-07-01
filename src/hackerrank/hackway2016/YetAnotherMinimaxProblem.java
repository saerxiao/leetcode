package hackerrank.hackway2016;

import java.util.Arrays;
import java.util.Scanner;

public class YetAnotherMinimaxProblem {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int N = in.nextInt();
			int[] A = new int[N];
			for (int i = 0; i < N; i++) {
				A[i] = in.nextInt();
			}
			System.out.println(minMaxScore(A));
		}
	}

	public static int minMaxScore(int[] A) {
		Arrays.sort(A);
		int p = 1;
		while (p <= A[A.length-1]) {
			p = p << 1;
		}
		p = p >> 1;
		int k = 0;
		for (int i = A.length - 1; i >= 0; i--) {
			if (A[i] < p) {
				k = i + 1;
				break;
			}
		}
		
		if (k == 0) {
			if (A[0] == 0) {
				return 0;
			} else {
				for (int i = 0; i < A.length; i++) {
					A[i] = A[i] - p;
				}
				return minMaxScore(A);
			}
		} else {
			int min = A[k] ^ A[0];
			for (int i = k; i < A.length; i++) {
				for (int j = 0; j < k; j++) {
					min = Math.min(min, A[i] ^ A[j]);
				}
			}
			return min;
		}		
	}
}

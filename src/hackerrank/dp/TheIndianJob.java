package hackerrank.dp;

import java.util.Scanner;

public class TheIndianJob {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			for (int t = 0; t < T; t++) {
				int N = in.nextInt();
				int G = in.nextInt();
				int[] A = new int[N];
				for (int i = 0; i < N; i++) {
					A[i] = in.nextInt();
				}
				if (isPossible(A, G)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}
	}

	public static boolean isPossible(int[] A, int G) {
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
		}
		if (sum > G * 2) {
			return false;
		}
		
		int[][] m = new int[A.length][G+1];
		for (int j = 0; j <= G; j++) {
			m[0][j] = 0;
		}
		for (int i = 0; i < A.length; i++) {
			m[i][0] = 0;
		}
		
		for (int i = 1; i < A.length; i++) {
			for (int j = 1; j <= G; j++) {
				m[i][j] = Math.max(m[i-1][j], m[i][j-1]);
				if (j - A[i] >= 0) {
					m[i][j] = Math.max(m[i][j], m[i-1][j-A[i]] + A[i]);
 				}
			}
		}
		if (sum - m[A.length-1][G] <= G) {
			return true;
		} else {
			return false;
		}
	}
}

package hackerrank.dp;

import java.util.Scanner;

public class BricksGame {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			for (int t = 0; t < T; t++) {
				int N = in.nextInt();
				long[] A = new long[N];
				for (int i = 0; i < N; i++) {
					A[i] = in.nextLong();
				}
				System.out.println(maxScore(A));
			}
		}
	}
	
	public static long maxScore(long[] A) {
		long[] m = new long[A.length];
		int[] s = new int[A.length];
		for (int i = A.length - 1; i >= 0; i--) {
			m[i] = A[i] + (i+1 < A.length && i+1+s[i+1] < A.length ? m[i+1+s[i+1]] : 0);
			s[i] = 1;
			if (i+1 < A.length) {
				long s2 = A[i] + A[i+1];
				if (i+2 < A.length && i+2+s[i+2] < A.length) {
					s2 += m[i+2+s[i+2]];
				}
				if (s2 > m[i]) {
					m[i] = s2;
					s[i] = 2;
				}
			}
			if (i+2 < A.length) {
				long s3 = A[i] + A[i+1] + A[i+2];
				if (i+3 < A.length && i+3+s[i+3] < A.length) {
					s3 += m[i+3+s[i+3]];
				}
				if (s3 > m[i]) {
					m[i] = s3;
					s[i] = 3;
				}
			}
		}
		return m[0];
	}

}

package dp;

import java.util.Scanner;

public class Candies {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int N = in.nextInt();
			int[] A = new int[N];
			for (int i = 0; i < N; i++) {
				A[i] = in.nextInt();
			}
			System.out.println(minCandies(A));
		}
	}

	public static long minCandies(int[] A) {
		int[] m = new int[A.length];
		m[0] = 1;
		
		for (int i = 1; i < A.length; i++) {
			if (A[i] > A[i-1]) {
				m[i] = m[i-1] + 1;
			} else {
				m[i] = 1;
			}
		}
		
		for (int i = A.length - 2; i >= 0; i--) {
			int t = m[i];
			if (A[i] > A[i+1]) {
				t = m[i+1] + 1;
			} else {
				t = 1;
			}
			m[i] = Math.max(m[i], t);
		}
		long sum = 0;
		for (int i = 0; i < m.length; i++) {
			sum = sum + Long.valueOf(m[i]);
		}
		
		return sum;
	}
	
	private static void print(int[] m) {
		for (int i = 0; i < m.length; i++) {
			System.out.print(m[i] + " ");
		}
		System.out.println();
	}
}
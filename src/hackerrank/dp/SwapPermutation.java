package hackerrank.dp;

import java.util.Scanner;

public class SwapPermutation {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int n = in.nextInt();
			int k = in.nextInt();
			System.out.print(exactKAdjacentSwap(n, k) + " ");
			System.out.print(atMostKSwap(n, k));
			System.out.println();
		}
	}
	
	public static int exactKAdjacentSwap(int n, int k) {
		int[][] m = new int[n][k+1];
		for (int i = 0; i < n; i++) {
			m[i][0] = 1;
		}
		for (int j = 1; j <= k; j++) {
			m[0][j] = 0;
			m[1][j] = 1;
		}
		
		int MOD = 1000000007;
		for (int i = 2; i < n; i++) {
			for (int j = 1; j <= k; j++) {
//				m[i][j] = (int) ((Long.valueOf(m[i-1][j]) + Long.valueOf(m[i][j-1])) % MOD);
				m[i][j] = (m[i-1][j] + m[i][j-1]) % MOD;
				if (j-i-1 >= 0) {
					m[i][j] = (m[i][j] + MOD - m[i-1][j-i-1]) % MOD;
				}
//				int s = j-i >= 0 ? j-i : 0;
//				for (int p = j; p >= s; p--) {
//					m[i][j] = (m[i][j] + m[i-1][p]) % MOD;
//				}
			}
		}
		
//		print(m);
		return m[n-1][k];
	}

	public static int atMostKSwap(int n, int k) {
		int[][] m = new int[n][k+1];
		for (int i = 0; i < n; i++) {
			m[i][0] = 1;
		}
		
		for (int j = 0; j <= k; j++) {
			m[0][j] = 1;
		}
		
		int MOD = 1000000007;
		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= k; j++) {
				m[i][j] = (int) ((Long.valueOf(m[i-1][j]) + Long.valueOf(m[i-1][j-1]) * Long.valueOf(i)) % MOD);
			}
		}
		
//		print(m);
		return m[n-1][k];
	}
	
	private static void print(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
}

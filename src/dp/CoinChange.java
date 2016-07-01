package dp;

import java.util.Scanner;

public class CoinChange {

	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)){
			int N = in.nextInt();
			int M = in.nextInt();
			int[] c = new int[M];
			for (int i = 0; i < M; i++) {
				c[i] = in.nextInt();
			}
			System.out.println(waysOfChange(N, c));
		}
	}
	
	public static long waysOfChange(int N, int[] c) {
		long[][] p = new long[N+1][c.length + 1];
		for (int i = 0; i <= N; i++) {
			p[i][0] = 0;
		}
		
		for (int j = 1; j <= c.length; j++) {
			p[0][j] = 1;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= c.length; j++) {
				p[i][j] = p[i][j-1];
				if (i - c[j-1] >= 0) {
					p[i][j] += p[i-c[j-1]][j];
				}
			}
		}
		return p[N][c.length];
	}
}

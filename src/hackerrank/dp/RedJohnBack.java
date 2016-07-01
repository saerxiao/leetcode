package hackerrank.dp;

import java.util.Scanner;

public class RedJohnBack {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			for (int t = 0; t < T; t++) {
				System.out.println(numberOfPrime(in.nextInt()));
			}
		}
	}
	
	public static int numberOfPrime(int N) {
		int[] m = new int[N+1];
		int[] p = new int[N+1];
		m[0] = 1;
		p[0] = 0;
		for (int i = 1; i <= N; i++) {
			m[i] = m[i-1];
			if (i >= 4) {
				m[i] += m[i-4];
			}
			p[i] = p[i-1];
			for (int j = m[i-1] + 1; j <= m[i]; j++) {
				if (isPrime(j)) {
					p[i]++;
				}
			}
		}
		return p[N];
	}

	private static boolean isPrime(int a) {
		if (a==1) return false;
		for (int i = 2; i <= Math.sqrt(a); i++) {
			if (a%i == 0) return false;
		}
		return true;
	}
}

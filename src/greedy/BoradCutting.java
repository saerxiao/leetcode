package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class BoradCutting {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			for (int i = 0; i < T; i++) {
				int m = in.nextInt();
				int n = in.nextInt();
				int[] x = new int[m-1];
				for (int p = 0; p < x.length; p++) {
					x[p] = in.nextInt();
				}
				int[] y = new int[n-1];
				for (int p = 0; p < y.length; p++) {
					y[p] = in.nextInt();
				}
				System.out.println(minCost(x,y));
			}
		}
	}

	public static long minCost(int[] x, int[] y) {
		Arrays.sort(x);
		Arrays.sort(y);
		int i = x.length - 1;
		int j = y.length - 1;
		int nx = 1;
		int ny = 1;
		long total = 0;
		long base = Long.valueOf(1000000007);
		while (i >= 0 && j >= 0) {
			if (x[i] > y[j]) {
				total += Long.valueOf(x[i])*Long.valueOf(nx);
				i--;
				ny++;
			} else {
				total += Long.valueOf(y[j])*Long.valueOf(ny);
				j--;
				nx++;
			}
		}
		if (j < 0) {
			for (int k = i; k >= 0; k--) {
				total += Long.valueOf(x[k]) * Long.valueOf(nx);
			}
		} else if (i < 0) {
			for (int k = j; k >= 0; k--) {
				total += Long.valueOf(y[k]) * Long.valueOf(ny);
			}
		}
		return total = total % base;
	}
}

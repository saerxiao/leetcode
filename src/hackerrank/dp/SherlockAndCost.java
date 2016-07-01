package hackerrank.dp;

import java.util.Scanner;

public class SherlockAndCost {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			for (int t = 0; t < T; t++) {
				int N = in.nextInt();
				int[] B = new int[N];
				for (int i = 0; i < N; i++) {
					B[i] = in.nextInt();
				}
				System.out.println(maxCost(B));
			}
			
		}
	}
	
	public static int maxCost(int[] B) {
		if (B.length == 1) return 0;
		
		int p = B[0] - 1; //A[i] = 1
		int q = Math.abs(B[0] - B[1]) > B[1] - 1 ? Math.abs(B[0] - B[1]) : B[1] - 1; // A[i] = B[i]
		for (int i = 2; i < B.length; i++) {
			int nextP = Math.max(p, q + B[i-1] - 1);
			q = p + B[i] - 1 > q + Math.abs(B[i] - B[i-1]) ? p + B[i] - 1 : q + Math.abs(B[i] - B[i-1]);
			p = nextP;
		}
		return Math.max(p, q);
	}

}
